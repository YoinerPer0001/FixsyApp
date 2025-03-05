package com.example.technician_complete_profile.presentation.ui.screens

sealed class TechState {

    object Idle : TechState() // Estado inicial
    object Loading : TechState() // Cargando
    data class ErrorEmail(val message: String) : TechState()
    object Success : TechState() // Éxito
    data class Error(val message: String) : TechState() // Error

}

sealed class SendState {

    object Idle : SendState() // Estado inicial
    object Loading : SendState() // Cargando
    data class ErrorEmail(val message: String) : SendState()
    object Success : SendState() // Éxito
    data class Error(val message: String) : SendState() // Error

}