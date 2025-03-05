package com.example.technician_complete_profile.data.repository

import com.example.technician_complete_profile.data.dto.SpecialtiesDTO
import com.example.technician_complete_profile.data.remote.SpecialtiesService
import com.example.technician_complete_profile.domain.repository.ISpecialtiesRepository
import javax.inject.Inject

class SpecialtiesRepository @Inject constructor(private val apiService: SpecialtiesService) : ISpecialtiesRepository {
    override suspend fun getAllSpecialties(): List<SpecialtiesDTO>? {
        try {

            val response = apiService.getAllSpecialties()
            return response.body()

        }catch (e: Exception){
            return emptyList()
        }
    }
}