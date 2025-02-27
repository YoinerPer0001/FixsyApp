package com.example.users.presentation.viewmodels.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.validators.Validator
import com.example.users.domain.usecases.interfaces.ILogin
import com.example.users.presentation.ui.screens.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCaseLogin: ILogin
) : ViewModel() {

    private val _loginState = MutableSharedFlow<LoginState>(replay = 1)
    val loginState: SharedFlow<LoginState> = _loginState


    fun onLogin(email: String, password: String, type: String) {


        viewModelScope.launch {
            _loginState.emit(LoginState.Loading)
            try {
                if (!Validator.isValidEmail(email)) {
                    delay(50)
                    _loginState.emit(LoginState.ErrorEmail("Correo electrónico no válido"))

                } else if (Validator.isValidPassword(password) != null){ // null es contraseña valida
                    delay(50)
                    _loginState.emit(LoginState.ErrorEmail(Validator.isValidPassword(password)!!))

                } else {
                    val response = useCaseLogin.login(email, password, type)
                    if (response != null) {
                        _loginState.emit(LoginState.Success(response))
                    } else {
                        _loginState.emit(LoginState.Error("Usuario o contraseña incorrecta"))
                    }
                }
            } catch (e: Exception) {
                _loginState.emit(LoginState.Error("Error de conexión"))
            }
        }


    }

}