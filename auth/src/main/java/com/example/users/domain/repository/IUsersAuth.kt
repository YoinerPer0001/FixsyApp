package com.example.users.domain.repository

import com.example.core.data.ClientRegisterRequest
import com.example.users.domain.models.BasicUserBM

interface IUsersAuth {
    suspend fun login(email:String , password: String, type:String): BasicUserBM?

    suspend fun clientRegister(client: ClientRegisterRequest): Pair<Boolean, String>
}