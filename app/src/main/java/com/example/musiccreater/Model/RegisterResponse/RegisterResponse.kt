package com.example.musiccreater.Model.RegisterResponse


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    val message: String? = null,
    val status: Int? = null,
    val token: String? = null,
    val user: User? = null
)
data class User(
    val createdAt: String? = null,
    val email: String? = null,
    val firstName: String? = null,
    @SerializedName("_id")
    val id: String? = null,
    val lastName: String? = null,
    val password: String? = null,
    val updatedAt: String? = null,
    val wishlist: List<Any?>? = null
)