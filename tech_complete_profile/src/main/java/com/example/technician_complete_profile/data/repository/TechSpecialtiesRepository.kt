package com.example.technician_complete_profile.data.repository

import android.util.Log
import com.example.technician_complete_profile.data.dto.TechEspRequest
import com.example.technician_complete_profile.data.dto.TechspDTO
import com.example.technician_complete_profile.data.dto.updateStateRequest
import com.example.technician_complete_profile.data.remote.TechniciansService
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository

import javax.inject.Inject

class TechSpecialtiesRepository @Inject constructor(private val apiService : TechniciansService) :
    ITechSpecialtiesRepository {
    override suspend fun getTechSpecialties(id:String): List<TechspDTO>? {
        try {
            val response = apiService.getTechSpecialties(id)

            if(!response.isSuccessful){
                return null
            }
            return response.body() ?: emptyList()

        }catch (e: Exception){
            return emptyList()
        }
    }

    override suspend fun setTechEsp(request: TechEspRequest): Pair<Boolean, String> {
        try {
            val response = apiService.setTechEspecialty(request)

            if(!response.isSuccessful) {
                return Pair(false, "")
            }

            return Pair(true, response.body()!!)

        }catch (e: Exception){
            return Pair(false, e.message.toString())
        }
    }

    override suspend fun updateState(id_user: String, state: String): Boolean? {
        try {


            val request = updateStateRequest(
                state = state
            )
            val response = apiService.update(id_user, request)
            if(!response.isSuccessful) {
                return false
            }

            return true

        }catch (e: Exception){
            return null
        }
    }

}