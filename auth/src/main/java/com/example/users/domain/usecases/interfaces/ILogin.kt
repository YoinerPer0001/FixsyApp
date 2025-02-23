package com.example.users.domain.usecases.interfaces

import com.example.users.domain.models.BasicUserBM

interface ILogin {

    suspend fun login(email:String, password:String, type:String) : BasicUserBM?
}