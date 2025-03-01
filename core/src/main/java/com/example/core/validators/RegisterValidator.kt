package com.example.core.validators

import android.util.Log
import androidx.core.text.isDigitsOnly
import org.apache.commons.validator.routines.EmailValidator


fun String.IsValidName(): Pair<Boolean, String?> {
    if (this.none { it.isDigit() } && this.isNotEmpty()) {
        return Pair(true, null)
    } else {
        var errorMessage = ""
        if (!this.none { it.isDigit() }) {
            errorMessage = "* El nombre no debe contener números"
        } else {
            errorMessage = "* Este campo obligatorio"
        }
        return Pair(false, errorMessage)
    }
}

fun String.isValidIdNumber(): Pair<Boolean, String?> {
    if (this.isDigitsOnly() && this.isNotEmpty()) {
        return Pair(true, null)
    } else {
        var errorMessage = ""
        if (!this.isDigitsOnly()) {
            errorMessage = "* El campo solo debe contener números"
        } else {
            errorMessage = "* Este campo obligatorio"
        }
        return Pair(false, errorMessage)
    }

}

fun String.IsvalidPhone(): Pair<Boolean, String?> {
    if (this.isDigitsOnly() && this.isNotEmpty() && this.length == 10) {
        return Pair(true, null)
    } else {
        var errorMessage = ""
        if (this.isEmpty()) {
            errorMessage = "* Este campo obligatorio"
        } else {
            errorMessage = "* Ingrese un numero de celular valido"
        }
        return Pair(false, errorMessage)
    }
}

fun String.isValidEmail(): Pair<Boolean, String?> {
    if (EmailValidator.getInstance().isValid(this)) {
        return Pair(true, null)
    } else {
        return Pair(false, "* Correo electronico no valido")
    }

}

fun String.isValidAdress(): Pair<Boolean, String?> {
    if (this.isNotEmpty()) {
        return Pair(true, null)
    } else {
        return Pair(false, "* Este campo obligatorio")
    }

}

fun String.isValidPassword(): Pair<Boolean, String?> {

    if (this.isNotEmpty() && this.length >= 8) {
        return Pair(true, null)
    } else {
        var errorMessage = ""
        if (this.length < 8) {

            errorMessage = "* La contraseña debe contener minimo 8 caracteres"
        } else {
            errorMessage = "* Este campo obligatorio"
        }
        return Pair(false, errorMessage)
    }

}

