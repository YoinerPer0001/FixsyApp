package com.example.users.domain.usecases.interfaces

import com.example.core.data.ClientRegisterRequest

interface IClientRegister {
    suspend fun clientRegister(client: ClientRegisterRequest): Pair<Boolean, String>
}