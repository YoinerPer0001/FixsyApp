package com.example.technician_complete_profile.domain.repository

import com.example.technician_complete_profile.data.dto.TechEspRequest
import com.example.technician_complete_profile.data.dto.TechspDTO


interface ITechSpecialtiesRepository {

    suspend fun getTechSpecialties(id:String): List<TechspDTO>?

    suspend fun setTechEsp(request: TechEspRequest): Pair<Boolean, String>

    suspend fun updateState(id_user:String, state: String ): Boolean?

}