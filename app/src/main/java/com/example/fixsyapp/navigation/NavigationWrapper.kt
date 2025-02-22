package com.example.fixsyapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.users.presentation.ui.screens.HomeClientScreen
import com.example.users.presentation.ui.screens.login.LoginScreen
import com.example.users.presentation.viewmodels.LoginViewModel

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Login){
        composable<Login> {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(loginViewModel){navController.navigate(Home)}
        }

        composable<Home> {
            HomeClientScreen()
        }
    }
}