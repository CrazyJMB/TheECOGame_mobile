package com.dds.theecogame.presentation.ranking.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class RankingViewModel() : ViewModel() {

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