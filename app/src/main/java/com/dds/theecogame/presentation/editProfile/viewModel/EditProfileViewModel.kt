package com.dds.theecogame.presentation.editProfile.viewModel

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dds.theecogame.common.Resource
import com.dds.theecogame.data.repository.UserRepositoryImpl
import com.dds.theecogame.domain.Application
import com.dds.theecogame.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class EditProfileViewModel() : ViewModel() {

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun saveImageLocal(context: Context, imageUri: Uri) {
        // Save the image
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File(storageDir, "avatar.jpg")

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

    fun saveImageRemote(imageUri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateAvatar(
                Application.getUser()!!.id,
                imageUri.toString()
            )
        }

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

}