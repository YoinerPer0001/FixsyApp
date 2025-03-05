package com.example.technician_complete_profile.domain.usecases.implementations

import com.example.technician_complete_profile.data.mappers.toSpecialtiesMN
import com.example.technician_complete_profile.domain.model.SpecialtiesMN
import com.example.technician_complete_profile.domain.repository.ISpecialtiesRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.IGetAllSpecialities
import javax.inject.Inject

class GetAllSpecialities @Inject constructor(private val repository : ISpecialtiesRepository) :
    IGetAllSpecialities {
    override suspend fun invoke(): List<SpecialtiesMN>? {
        val response = repository.getAllSpecialties()
        return response?.toSpecialtiesMN()
    }
}