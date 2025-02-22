package com.example.core.validators

import org.apache.commons.validator.routines.EmailValidator

object Validator {

    fun isValidEmail(email: String): Boolean {
        return EmailValidator.getInstance().isValid(email)
    }

    fun isValidPassword(password: String): String? {
        if(password.isEmpty()) return "Ingrese la contraseña"
        if(password.length < 8) return "La contraseña debe ser minimo de 8 digitos"

        return null
    }


}