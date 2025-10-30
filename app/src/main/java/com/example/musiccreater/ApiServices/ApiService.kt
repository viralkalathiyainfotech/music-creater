package com.example.musiccreater.ApiServices

import com.example.musiccreater.Model.LoginResponse.LoginResponse
import com.example.musiccreater.Model.RegisterResponse.RegisterResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
//    @NoAuth
//    @POST("api/auth/google")
//    suspend fun googleLogin(@Body body: Map<String, String>): Response<LoginResponse>

    @NoAuth
    @POST("api/userLogin")
    suspend fun loginUser(@Body credentials: Map<String, String>): Response<LoginResponse>

    @NoAuth
    @POST("api/auth/register")
    fun registerUser(@Body request: Map<String, String>): Call<RegisterResponse>
}