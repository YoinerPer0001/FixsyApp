package com.example.users.data.remote

import com.example.users.data.dto.LoginRequest
import com.example.users.data.dto.UserBasic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<UserBasic>
}