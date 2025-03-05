package com.example.technician_complete_profile.presentation.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.core.components.Buttons.ButtonPrimary
import com.example.core.components.text.TextPrimary
import com.example.core.components.textfields.InputForm
import com.example.tech_complete_profile.R

@Composable
fun SecondForm(){

    val imageExperience = painterResource(R.drawable.logo_exp)

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        Image(modifier = Modifier.width(200.dp), painter = imageExperience, contentDescription = "logo exp", contentScale = ContentScale.Crop)

        TextPrimary("Describe tu experiencia!")
        InputForm(label = "Experience", transformation = VisualTransformation.None, response = { value ->

        })

        ButtonPrimary("Enviar") {
            //send data to db
        }
    }
}