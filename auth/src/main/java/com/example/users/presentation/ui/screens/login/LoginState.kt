package com.example.users.presentation.ui.screens.login

import com.example.users.domain.models.BasicUserBM

sealed class LoginState {
    object Idle : LoginState() // Estado inicial
    object Loading : LoginState() // Cargando
    data class ErrorEmail (val message:String) : LoginState()
    data class Success(val user: BasicUserBM) : LoginState() // Éxito
    data class Error(val message: String) : LoginState() // Error
}