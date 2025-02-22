package com.example.users.data.dto

data class LoginRequest(
    val email: String,
    val password: String,
    val type: String
)
