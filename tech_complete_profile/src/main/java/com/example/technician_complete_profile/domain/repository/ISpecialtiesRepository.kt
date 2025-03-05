package com.example.technician_complete_profile.domain.repository

import com.example.technician_complete_profile.data.dto.SpecialtiesDTO

interface ISpecialtiesRepository {
    suspend fun getAllSpecialties(): List<SpecialtiesDTO>?
}