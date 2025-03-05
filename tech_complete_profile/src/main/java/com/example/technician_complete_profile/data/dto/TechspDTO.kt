package com.example.technician_complete_profile.data.dto

//general model dto technician's specialties
data class TechspDTO(
    val id: String,
    val tec_info: TechDto,
    val esp_info: SpecialtiesDTO
)
