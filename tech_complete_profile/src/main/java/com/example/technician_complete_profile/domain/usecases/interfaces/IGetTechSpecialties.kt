package com.example.technician_complete_profile.domain.usecases.interfaces

import com.example.technician_complete_profile.domain.model.TechSpMN

interface IGetTechSpecialties {
    suspend operator fun invoke (id:String) : List<TechSpMN>?
}