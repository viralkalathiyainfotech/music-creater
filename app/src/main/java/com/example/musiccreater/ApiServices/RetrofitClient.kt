package com.example.musiccreater.ApiServices

import android.util.Log
import com.example.musiccreater.Utils.SharedPreferences.gson
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.apply
import kotlin.isInitialized
import kotlin.jvm.java
import kotlin.text.isNullOrBlank
import kotlin.text.isNullOrEmpty
import kotlin.text.removePrefix

object RetrofitClient {
    private const val BASE_URL = "http://192.168.29.52:5000/"
    private lateinit var apiServiceInstance: ApiService
    private const val IMAGE_PATH = "uploads/"

    fun init() {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        Log.d("ProfileViewModel", "Response code: ${AuthInterceptor()}")
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(AuthInterceptor()) // Token
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .connectionPool(ConnectionPool(5, 5, TimeUnit.MINUTES))
            .followRedirects(true)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        apiServiceInstance = retrofit.create(ApiService::class.java)
    }

    val apiService: ApiService
        get() {
            if (!::apiServiceInstance.isInitialized) {
                throw kotlin.IllegalStateException("RetrofitClient not initialized. Call init() first.")
            }
            return apiServiceInstance
        }

    fun getImageUrl(path: String?): String {
        if (path.isNullOrBlank()) return ""
        return BASE_URL + path.removePrefix("/")
    }

    fun getImageUrlPath(path: String?): String {
        return if (!path.isNullOrEmpty()) BASE_URL + IMAGE_PATH + path else ""
    }
}
