package com.example.fixsyapp.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.core.data.PreferencesManager
import com.example.core.navigation.HomeScreen
import com.example.core.navigation.Login
import com.example.core.navigation.Register
import com.example.core.navigation.TechnicianHome
import com.example.technician_complete_profile.presentation.ui.screens.TecCompleteScreen
import com.example.technician_complete_profile.presentation.viewmodels.FormCompletePerfilVM
import com.example.users.presentation.ui.screens.HomeEmpty
import com.example.users.presentation.ui.screens.login.LoginScreen
import com.example.users.presentation.ui.screens.register.FirstRegister
import com.example.users.presentation.ui.screens.register.RegisterScreen
import com.example.users.presentation.ui.screens.register.SecondForm
import com.example.users.presentation.viewmodels.login.LoginViewModel
import com.example.users.presentation.viewmodels.register.ClientRegisterViewModel
import com.example.users.presentation.viewmodels.register.FormRegisterVM

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val viewModelRegisterForm: FormRegisterVM = hiltViewModel()

    NavHost(navController, startDestination = Login) {
        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(loginViewModel){ destination ->
                navController.navigate(destination)
            }
            viewModelRegisterForm.clearData()

        }


        composable<Register> {
            val registerViewModel: ClientRegisterViewModel = hiltViewModel()
            RegisterScreen(registerViewModel,viewModelRegisterForm){ destination ->
                navController.navigate(destination)
            }
        }

        composable<TechnicianHome> { backStackEntry ->
//            val numberForm :TechnicianHome = backStackEntry.toRoute()
            val viewModelForm : FormCompletePerfilVM = hiltViewModel()
            TecCompleteScreen(viewModelForm){ destination ->
                if(destination == HomeScreen){
                    navController.navigate(destination){
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                    }

                }else{
                    navController.navigate(destination)
                }

            }
        }

        composable<HomeScreen> {
            HomeEmpty()
        }


    }
}