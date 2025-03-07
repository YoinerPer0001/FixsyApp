package com.example.users.presentation.viewmodels.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.ClientRegisterRequest
import com.example.core.navigation.Login
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
        Log.d("INFO", client.toString())
        Log.d("Client", client.toString())
        viewModelScope.launch {
            registerState.emit(RegisterState.Loading)
            try {

                val (response, message) = clientRegisterUsercase.clientRegister(client)
                if (!response) {
                    registerState.emit(RegisterState.Error(message))
                } else {
                    //redirect to login

                    registerState.emit(RegisterState.Success(Login))
                }


            } catch (e: Exception) {
                registerState.emit(RegisterState.Error("Error de conexión, intente nuevamente"))
            }
        }
    }
}