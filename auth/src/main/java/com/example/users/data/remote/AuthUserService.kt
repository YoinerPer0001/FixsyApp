package com.example.users.data.remote

import com.example.core.data.ClientRegisterRequest
import com.example.users.data.dto.LoginRequest
import com.example.users.data.dto.UserBasic
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthUserService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<UserBasic>

    @POST("register")
    suspend fun register(@Body request: ClientRegisterRequest): Response<String>

}