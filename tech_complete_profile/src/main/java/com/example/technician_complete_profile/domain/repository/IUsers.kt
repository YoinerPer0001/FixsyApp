package com.example.technician_complete_profile.domain.repository

import com.example.technician_complete_profile.data.dto.UpdatePhotoRequest

interface IUsers {

    suspend fun updatePhoto(id:String, request: UpdatePhotoRequest): Boolean?


}