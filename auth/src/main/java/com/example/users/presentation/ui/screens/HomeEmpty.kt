package com.example.users.presentation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeEmpty (){
    Scaffold() {
        Text("HOME TEST", fontSize = 50.sp)
    }
}