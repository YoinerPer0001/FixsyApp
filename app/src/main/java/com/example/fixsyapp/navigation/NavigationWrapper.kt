package com.example.fixsyapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Home
import com.example.core.navigation.Login
import com.example.core.navigation.Register
import com.example.users.presentation.ui.screens.login.LoginScreen
import com.example.users.presentation.ui.screens.register.RegisterScreen
import com.example.users.presentation.viewmodels.login.LoginViewModel
import com.example.users.presentation.viewmodels.register.ClientRegisterViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Login) {
        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(loginViewModel){ destination ->
                navController.navigate(destination){
                    if(destination == Login){
                        popUpTo<Login>{inclusive = false} // borrar pila de navegacion
                    }
                }
            }
        }

        composable<Register> {
            val viewModel: ClientRegisterViewModel = hiltViewModel()
            RegisterScreen(viewModel){ destination ->
                navController.navigate(destination){
                    if(destination == Login){
                        popUpTo<Login>{inclusive = false} // borrar pila de navegacion
                    }
                }
            }
        }


    }
}