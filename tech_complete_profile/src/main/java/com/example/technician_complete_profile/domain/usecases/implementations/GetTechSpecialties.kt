package com.example.technician_complete_profile.domain.usecases.implementations

import com.example.technician_complete_profile.data.mappers.toTechSpMN
import com.example.technician_complete_profile.domain.model.TechSpMN
import com.example.technician_complete_profile.domain.repository.ITechSpecialtiesRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetTechSpecialties
import javax.inject.Inject

class GetTechSpecialties @Inject constructor( private val repository : ITechSpecialtiesRepository):
    IGetTechSpecialties {
    override suspend operator fun invoke(id:String): List<TechSpMN>? {
        try {
            val response = repository.getTechSpecialties(id)
            val objecto = response?.toTechSpMN()
            return  objecto

        }catch (e:Exception){
            return null
        }
    }
}