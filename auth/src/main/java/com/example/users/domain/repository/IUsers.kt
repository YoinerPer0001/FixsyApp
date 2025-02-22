package com.example.users.domain.repository

import com.example.users.domain.models.BasicUserBM

interface IUsers {
    suspend fun login(email:String , password: String, type:String): BasicUserBM?
}