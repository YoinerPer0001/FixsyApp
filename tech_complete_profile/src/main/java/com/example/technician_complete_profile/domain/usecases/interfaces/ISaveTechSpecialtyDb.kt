package com.example.technician_complete_profile.domain.usecases.interfaces

import retrofit2.Response

interface ISaveTechSpecialtyDb {
    suspend operator  fun invoke (id_tech: String, id_esp: String) : Pair<Boolean, String>
}