package com.example.users.presentation.viewmodels.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.PreferencesManager
import com.example.core.navigation.HomeScreen
import com.example.core.navigation.TechnicianHome
import com.example.core.utils.UserType
import com.example.core.validators.Validator
import com.example.users.data.mappers.toUserCore
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
    private val useCaseLogin: ILogin,
    private val userPreferences: PreferencesManager
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

                } else if (Validator.isValidPassword(password) != null) { // null es contraseña valida
                    delay(50)
                    _loginState.emit(LoginState.ErrorEmail(Validator.isValidPassword(password)!!))

                } else {
                    val response = useCaseLogin.login(email, password, type)
                    if (response != null) { // logueado
                        userPreferences.saveUserId(response.id)
                        userPreferences.saveUserBasicInfo(response.toUserCore())
//                        Log.d("USER LOGUEADO",response.toUserCore().toString())

                        val userType = UserType.fromString(type)
                        if (userType.value == UserType.Tecnic.value) { // if user is technician we verified all fields are completed
                            _loginState.emit(
                                LoginState.Success(
                                    response,
                                    TechnicianHome
                                )
                            )// if photo not found
                        } else {
                            //send to client home
                            _loginState.emit(LoginState.Success(response, HomeScreen))
                        }

                    } else {
                        _loginState.emit(LoginState.Error("Usuario o contraseña incorrecta"))
                    }
                }
            } catch (e: Exception) {
                _loginState.emit(LoginState.Error(e.message.toString()))

            } finally {
                delay(50)
                _loginState.emit(LoginState.Idle)
            }
        }


    }

}