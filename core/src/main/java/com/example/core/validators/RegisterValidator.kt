package com.example.core.validators

import android.util.Log
import androidx.core.text.isDigitsOnly
import org.apache.commons.validator.routines.EmailValidator


fun String.IsValidName (): Boolean{
    return this.isNotEmpty() && this.matches(Regex("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+\$"))
}

fun String.isValidIdNumber():Boolean{
    return this.isDigitsOnly()
}

fun String.IsvalidPhone():Boolean {
    return this.isDigitsOnly() || this.isNotEmpty()
}

fun String.isValidEmail(): Boolean {
    return EmailValidator.getInstance().isValid(this)
}

fun String.isValidAdress() :Boolean{
    return this.isNotEmpty()
}

fun String.isValidPassword(): Boolean {
    Log.d("Pass", this)
    if(this.isEmpty() || this.length < 8) {
        return false
    }

    return true
}

