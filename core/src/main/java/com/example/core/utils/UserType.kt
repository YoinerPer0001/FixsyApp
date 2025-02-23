package com.example.core.utils

sealed class UserType(val value: String) {
    object Client : UserType("client")
    object Tecnic : UserType("tecnic")
    object Admin : UserType("admin")

    companion object {
        fun fromString(type: String) : UserType {
            return when (type) {
                "Técnico" -> Tecnic
                "Administrador" -> Admin
                else -> Client
            }
        }
    }
}