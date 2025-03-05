package com.example.technician_complete_profile.domain.usecases.interfaces

import com.example.technician_complete_profile.domain.model.SpecialtiesMN

interface IGetAllSpecialities {
    suspend operator fun invoke(): List<SpecialtiesMN>?
}