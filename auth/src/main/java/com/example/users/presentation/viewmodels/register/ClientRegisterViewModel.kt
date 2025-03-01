package com.example.users.presentation.viewmodels.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.ClientRegisterRequest
import com.example.core.validators.IsValidName
import com.example.core.validators.IsvalidPhone
import com.example.core.validators.isValidEmail
import com.example.core.validators.isValidIdNumber
import com.example.core.validators.isValidPassword
import com.example.users.domain.usecases.interfaces.IClientRegister
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientRegisterViewModel @Inject constructor(
    private val clientRegisterUsercase: IClientRegister
) : ViewModel() {

    private val registerState = MutableSharedFlow<RegisterState>(replay = 1)
    val _registerState: SharedFlow<RegisterState> = registerState

    fun onRegister(client: ClientRegisterRequest) {
        Log.d("Client", client.toString())
        viewModelScope.launch {
            registerState.emit(RegisterState.Loading)
            try {
                if (!client.name.IsValidName().first) {
                    delay(50)
                    registerState.emit(RegisterState.Error("El nombre no debe contener valores numericos"))
                } else if (!client.email.isValidEmail().first) {
                    delay(50)
                    registerState.emit(RegisterState.Error("Correo electronico no valido"))
                } else if (!client.id_number.toString().isValidIdNumber().first) {
                    delay(50)
                    registerState.emit(RegisterState.Error("Numero de identificacion no valido"))
                } else if (!client.phone.toString().IsvalidPhone().first) {
                    delay(50)
                    registerState.emit(RegisterState.Error("Numero de celular no valido"))
                } else if (!client.password.isValidPassword().first) {
                    delay(50)
                    registerState.emit(RegisterState.Error("Contraseña no valida, minimo 8 caracteres"))
                } else {
                    val (response, message) = clientRegisterUsercase.clientRegister(client)
                    if(!response){
                        registerState.emit(RegisterState.Error(message))
                    }else{
                        registerState.emit(RegisterState.Success)
                    }

                }


            } catch (e: Exception) {
                registerState.emit(RegisterState.Error("Error de conexión"))
            }
        }
    }
}