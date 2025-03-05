package com.example.technician_complete_profile.domain.usecases.implementations

import android.util.Log
import com.example.technician_complete_profile.data.dto.UpdatePhotoRequest
import com.example.technician_complete_profile.data.repository.UsersRepository
import com.example.technician_complete_profile.domain.usecases.interfaces.IUpdatePhotoUser
import javax.inject.Inject

class UpdatePhotoUser @Inject constructor(private val repository: UsersRepository) : IUpdatePhotoUser {
    override suspend fun invoke(id: String, url: String): Boolean? {
        val request = UpdatePhotoRequest(
            perfil_photo = url
        )

        val response = repository.updatePhoto(id, request)

        return response

    }
}