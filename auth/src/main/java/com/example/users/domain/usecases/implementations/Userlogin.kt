package com.example.users.domain.usecases.implementations

import com.example.core.utils.UserType
import com.example.users.domain.models.BasicUserBM
import com.example.users.domain.repository.IUsers
import com.example.users.domain.usecases.interfaces.ILogin
import javax.inject.Inject

class Userlogin @Inject constructor(private val repository: IUsers) : ILogin {
    override suspend fun login(email: String, password: String, type: String): BasicUserBM? {
        try {
            val userType = UserType.fromString(type)
            val response =repository.login(email, password, userType.value)
            return response

        } catch (e: Exception) {
            return null
        }
    }

}