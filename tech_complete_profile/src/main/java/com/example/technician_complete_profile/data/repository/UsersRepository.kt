package com.example.technician_complete_profile.data.repository

import android.util.Log
import com.example.technician_complete_profile.data.dto.UpdatePhotoRequest
import com.example.technician_complete_profile.data.remote.UpdateUserPhotoService
import com.example.technician_complete_profile.domain.repository.IUsers
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val UpdatePhotoService: UpdateUserPhotoService
) : IUsers {
    override suspend fun updatePhoto(id:String, request: UpdatePhotoRequest): Boolean? {

        try {
            val response = UpdatePhotoService.update(id,request)
            Log.d("RESPONSE DB", response.toString())
            if(!response.isSuccessful){
                return null
            }

            return true

        }catch (e:Exception){
            return null
        }

    }
}