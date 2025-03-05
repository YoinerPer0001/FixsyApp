package com.example.technician_complete_profile.data.remote

import com.example.technician_complete_profile.data.dto.SpecialtiesDTO
import com.example.technician_complete_profile.data.dto.TechEspRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SpecialtiesService {
    @GET("/specialties")
    suspend fun getAllSpecialties(): Response<List<SpecialtiesDTO>>


}