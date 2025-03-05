package com.example.technician_complete_profile.domain.usecases.implementations

import com.example.technician_complete_profile.data.dto.TechEspRequest
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.ISaveTechSpecialtyDb
import javax.inject.Inject

class SaveTechSpecialtyDb @Inject constructor(private val repository : ITechSpecialtiesRepository) : ISaveTechSpecialtyDb {
    override suspend fun invoke(id_tech: String, id_esp: String): Pair<Boolean, String> {
        val tech = TechEspRequest(
            id_esp= id_esp,
            id_tech = id_tech
        )
        val response = repository.setTechEsp(tech)

        return response
    }
}