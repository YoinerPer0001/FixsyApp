package com.example.technician_complete_profile.domain.model

data class TechMN(
    val id: String,
    val user_id : String,
    val experience: String?,
    val certificates: String?,
    val qualification: Float?,
    val state : String
)
