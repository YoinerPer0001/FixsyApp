package com.example.technician_complete_profile.data.remote

import com.example.technician_complete_profile.data.dto.UpdatePhotoRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface UpdateUserPhotoService {

    @PUT("/user/update/{id}")
    suspend fun update (@Path("id") id:String, @Body request: UpdatePhotoRequest): Response<List<Int>>


}