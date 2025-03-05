package com.example.users.domain.models

data class BasicUserBM(
    val id: String,
    val id_tech: String,
    val id_number : String,
    val name: String,
    val email: String,
    val phone: String,
    val perfil_photo: String?,
    val experience: String?,
    val certificates: String?,
    val qualification: Float?,
    val state: String?
)
