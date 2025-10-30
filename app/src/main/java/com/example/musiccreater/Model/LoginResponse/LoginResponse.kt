package com.example.musiccreater.Model.LoginResponse


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val message: String? = null,
    val success: Boolean? = null,
    val token: String? = null,
    val user: User? = null
)
data class User(
    val createdAt: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    val gender: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    val lastName: String? = null,
    val mobile: String? = null,
    val otp: Int? = null,
    val password: String? = null,
    val photo: String? = null,
    val updatedAt: String? = null,
    val wishlist: List<String?>? = null
)