package com.example.users.data.repository

import android.util.Log
import com.example.core.data.ClientRegisterRequest
import com.example.users.data.dto.LoginRequest
import com.example.users.data.mappers.toUserBM
import com.example.users.data.remote.UserService
import com.example.users.domain.models.BasicUserBM
import com.example.users.domain.repository.IUsers
import javax.inject.Inject


class UserImplementation @Inject constructor(
    private val userService: UserService
) : IUsers {

    override suspend fun login(email: String, password: String, type: String): BasicUserBM? {
        try {

            val request = LoginRequest(email = email, password =  password, type =  type);
            val response = userService.login(request)
            if(!response.isSuccessful){
                Log.d("Warning:", "Usuario o contraseña incorrecta")
                return null
            }

            return response.body()?.toUserBM()

        }catch (e: Exception){
            Log.d("Error:", e.message ?: "error no identified")
            return null
        }
    }

    override suspend fun clientRegister(client: ClientRegisterRequest): Pair<Boolean, String> {

        try {
            val response = userService.register(client)

            if(response.isSuccessful)
            {
                return Pair(true, response.body()!!)

            }else{
                val errorMessaje = when (response.code()){

                    409 -> "El usuario ya se encuentra registrado"
                    else -> "Error desconocido. Intente de nuevo"
                }

                return Pair(false, errorMessaje)

            }



        }catch (e:Exception){
            Log.d("Error:", e.message ?: "error no identified")
            return Pair(false, "Error en el servidor")
        }
    }


}