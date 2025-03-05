package com.example.technician_complete_profile.data.remote

import com.example.technician_complete_profile.data.dto.TechEspRequest
import com.example.technician_complete_profile.data.dto.TechspDTO
import com.example.technician_complete_profile.data.dto.updateStateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TechniciansService {
    @GET("/technicians/especialties/{id}")
    suspend fun getTechSpecialties(@Path("id") id:String): Response<List<TechspDTO>>

    @POST("/technicians/especialties/create")
    suspend fun setTechEspecialty(@Body request: TechEspRequest): Response<String>

    @PUT("/tech/update/{id}")
    suspend fun update(@Path("id") id:String, @Body request: updateStateRequest): Response<List<Int>>
}