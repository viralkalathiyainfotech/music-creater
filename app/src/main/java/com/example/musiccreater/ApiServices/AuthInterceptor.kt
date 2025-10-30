package com.example.musiccreater.ApiServices

import android.util.Log
import com.example.musiccreater.Utils.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = SharedPreferences.getString("token")
        Log.d("AuthInterceptor", "Token from SharedPreferences: $token")

        val requestBuilder = chain.request().newBuilder()
        if (!token.isNullOrEmpty()) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }
        return chain.proceed(requestBuilder.build())
    }
}
