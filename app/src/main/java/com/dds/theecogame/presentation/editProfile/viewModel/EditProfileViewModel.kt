package com.dds.theecogame.presentation.editProfile.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.CountDownLatch

class EditProfileViewModel() : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun saveImageLocal(context: Context, imageUri: Uri) {
        // Save the image
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(storageDir, "avatar.png")

        try {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val outputStream = FileOutputStream(imageFile)
            val buf = ByteArray(1024)
            var len: Int
            while (inputStream!!.read(buf).also { len = it } > 0) {
                outputStream.write(buf, 0, len)
            }
            inputStream.close()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun saveImageRemote(file: File): Boolean {
        val url = "https://api.jorma28j.upv.edu.es/users/${Application.getUser()!!.id}/avatar"

        var isSuccess = false
        val latch = CountDownLatch(1) // Contador para esperar la llamada al Callback

        uploadImage(file, url) { result ->
            isSuccess = result
            latch.countDown() // Reduce el contador cuando el Callback se ha llamado
        }

        try {
            latch.await() // Espera a que el contador llegue a cero
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return isSuccess
    }


    fun updateUser(
        userId: Int,
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            userRepository.updateUser(userId, username, name, surname, email, password).collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {}
                    is Resource.Error -> {}
                }
            }
        }
    }

    fun uploadImage(imageFile: File, url: String, callback: (Boolean) -> Unit) {
        val client = OkHttpClient()

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "image",
                imageFile.name,
                RequestBody.create(MediaType.parse("image/*"), imageFile)
            )
            .build()

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
                callback(false) // Llama al Callback con el resultado false en caso de fallo
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                val isSuccess = response.isSuccessful
                response.close()
                callback(isSuccess) // Llama al Callback con el resultado de éxito o fallo
            }
        })
    }

    suspend fun loadImageFromUrl(imageUrl: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()

                val inputStream: InputStream = connection.inputStream
                val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
                inputStream.close()

                bitmap
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }
}