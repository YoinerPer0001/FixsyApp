package com.example.users.presentation.viewmodels.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.core.validators.IsValidName
import com.example.core.validators.IsvalidPhone
import com.example.core.validators.isValidAdress
import com.example.core.validators.isValidEmail
import com.example.core.validators.isValidIdNumber
import com.example.core.validators.isValidPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FormRegisterVM @Inject constructor(

) : ViewModel() {

    private var _userName = mutableStateOf("")
    val userName: State<String> = _userName
    private var _userLastName = mutableStateOf("")
    var userLastName: State<String> = _userLastName
    private var _userPhone = mutableStateOf("")
    var userPhone: State<String> = _userPhone
    private var _userAdress = mutableStateOf("")
    var userAdress: State<String> = _userAdress
    private var _idType = mutableStateOf("")
    var idType : State<String> = _idType
    private var _userId = mutableStateOf("")
    var userId:State<String> = _userId
    private var _firstPass = mutableStateOf("")
    var firstPass:State<String> = _firstPass
    private var _userEmail = mutableStateOf("")
    var userEmail:State<String> = _userEmail
    private var _secondPass = mutableStateOf("")
    var secondPass:State<String> = _secondPass
    private var _userType = mutableStateOf("")
    var userType:State<String> = _userType


    //errores
    private val _nameError = mutableStateOf<String?>(null)
    val nameError: State<String?> = _nameError
    private val _lastNameError = mutableStateOf<String?>(null)
    val lastNameError: State<String?> = _lastNameError
    private val _phoneError = mutableStateOf<String?>(null)
    val phoneError: State<String?> = _phoneError
    private val _userAddressError = mutableStateOf<String?>(null)
    val userAddressError: State<String?> = _userAddressError
    private val _userIdError = mutableStateOf<String?>(null)
    val userIdError: State<String?> = _userIdError
    private var _firstPassError = mutableStateOf<String?>(null)
    var firstPassError:State<String?> = _firstPassError
    private var _userEmailError = mutableStateOf<String?>(null)
    var userEmailError:State<String?> = _userEmailError
    private var _secondPassError = mutableStateOf<String?>(null)
    var secondPassError:State<String?> = _secondPassError

    fun updateName(value: String) {
        _userName.value = value
    }

    fun updateLastName(value: String) {
        _userLastName.value = value
    }

    fun updatePhone(value: String) {
        _userPhone.value = value
    }

    fun updateAddress(value: String) {
        _userAdress.value = value
    }

    fun updateIdType(value:String){
        _idType.value = value
    }

    fun updateUserId(value:String){
        _userId.value = value
    }

    fun updateEmail(value:String){
        _userEmail.value = value
    }

    fun updateFirstPass(value:String){
        _firstPass.value = value
    }

    fun updateSecondPass(value:String){
        _secondPass.value = value
    }

    fun updateUserType(value:String){
        _userType.value = value
    }

    fun validateForm() : Boolean {
        val isValidName = _userName.value.IsValidName()
        val isValidLastName = _userLastName.value.IsValidName()
        val isValidPhone = _userPhone.value.IsvalidPhone()
        val isValidAddress = _userAdress.value.isValidAdress()


        _nameError.value = if(!isValidName.first) isValidName.second else null
        _phoneError.value = if(!isValidPhone.first) isValidPhone.second else null
        _lastNameError.value = if(!isValidLastName.first) isValidLastName.second else null
        _userAddressError.value = if(!isValidAddress.first) isValidAddress.second else null


        return isValidName.first && isValidLastName.first && isValidPhone.first && isValidAddress.first
    }

    fun validateSecondForm() :Boolean{
        val isValidUserId = _userId.value.isValidIdNumber()
        _userIdError.value = if(!isValidUserId.first) isValidUserId.second else null

        return isValidUserId.first
    }

    fun validateThirdForm():Boolean {
        val isValidEmail = _userEmail.value.isValidEmail()
        val isValidFirstPass = _firstPass.value.isValidPassword()
        val isValidSecondPass = _secondPass.value.isValidPassword()

        _userEmailError.value = if(!isValidEmail.first) isValidEmail.second else null
        _firstPassError.value = if(!isValidFirstPass.first) isValidFirstPass.second else null
        _secondPassError.value = if(!isValidSecondPass.first) isValidSecondPass.second else null

        if(isValidFirstPass.first && isValidSecondPass.first){
            if(firstPass.value != secondPass.value){
                _secondPassError.value = "Las contraseñas no coinciden, verifique"
                return false
            }
        }


        return isValidEmail.first && isValidFirstPass.first && isValidSecondPass.first
    }

    fun clearData (){
        _userName.value = ""
        _userLastName.value = ""
        _userAdress.value = ""
        _userPhone.value = ""
        _userId.value = ""
        _firstPass.value = ""
        _secondPass.value = ""
        _userEmail.value = ""
        _nameError.value = null
        _phoneError.value = null
        _userIdError.value = null
        _lastNameError.value = null
        _firstPassError.value = null
        _secondPassError.value= null
        _userEmailError.value = null
        _userAddressError.value = null
    }


}