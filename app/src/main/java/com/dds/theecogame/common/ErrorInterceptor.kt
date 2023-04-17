package com.dds.theecogame.common

import okhttp3.Interceptor
import okhttp3.Response
import java.net.ConnectException
import java.net.SocketTimeoutException

class ErrorInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        try {
            return chain.proceed(request)

        } catch (e: ConnectException) {
            throw ApiException("Cannot connect to the server")
        } catch (e: SocketTimeoutException) {
            throw ApiException("Timeout reached")
        }
    }
}