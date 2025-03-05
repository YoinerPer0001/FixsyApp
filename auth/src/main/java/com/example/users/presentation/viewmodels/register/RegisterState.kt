package com.example.users.presentation.viewmodels.register

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Error(val message:String) : RegisterState()
    data class Success(val destination: Any): RegisterState()
}