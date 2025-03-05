package com.example.technician_complete_profile.data.dto

data class TechDto(
    val id: String,
    val user_id : String,
    val experience: String?,
    val certificates: String?,
    val qualification: Float?,
    val state : String
)
