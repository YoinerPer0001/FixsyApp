package com.example.users.domain.usecases.implementations

import com.example.core.data.ClientRegisterRequest
import com.example.core.utils.UserType
import com.example.users.domain.repository.IUsers
import com.example.users.domain.usecases.interfaces.IClientRegister
import javax.inject.Inject

class ClientRegister @Inject constructor(private val userRepository : IUsers) : IClientRegister{

    override suspend fun clientRegister(client: ClientRegisterRequest): Pair<Boolean, String> {
        try {
            if(!client.validate()){
                return Pair(false, "Datos no validos")
            }
            val clientType = UserType.fromString(client.type)
            client.type = clientType.value
            val response = userRepository.clientRegister(client)
            return response

        }catch (e:Exception){
            return Pair(false, "Error en el servidor")
        }
    }

}