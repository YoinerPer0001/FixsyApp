package com.example.technician_complete_profile.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.core.components.Buttons.ButtonPrimary
import com.example.core.components.text.TextPrimary
import com.example.core.components.textfields.DropDown
import com.example.core.components.textfields.DropDownPair
import com.example.core.components.textfields.InputForm
import com.example.core.navigation.TechnicianHome
import com.example.tech_complete_profile.R
import com.example.technician_complete_profile.domain.model.SpecialtiesMN
import com.example.technician_complete_profile.presentation.viewmodels.FormCompletePerfilVM
import es.dmoral.toasty.Toasty
import java.io.Serializable

@Composable
fun ThirdForm(viewmodel: FormCompletePerfilVM, list: List<SpecialtiesMN>?){

    val imageExperience = painterResource(R.drawable.esp_logo)

    val espSaved by viewmodel.sendState.collectAsState("")

    val context = LocalContext.current

    LaunchedEffect(espSaved) {
        when (espSaved){

            is SendState.Success -> {Toasty.success(context, "Enviado con exito", Toast.LENGTH_SHORT).show()}

            is SendState.Error -> {
                val errorMessage = (espSaved as SendState.Error).message
                Toasty.error(context,errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }


    val listString = mutableListOf<Pair<String, String>>()

    list?.forEach { value->
        listString.add(Pair(value.id, value.name ?: ""))
    }

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        Image(modifier = Modifier.width(200.dp).height(150.dp), painter = imageExperience, contentDescription = "logo exp", contentScale = ContentScale.Fit)

        TextPrimary("Elije tu especialidad!")

        if(listString.isNotEmpty()){
            DropDownPair("Especialidad", listString ) { id->
                viewmodel.saveUserEsp(id)
            }
        }

        ButtonPrimary("Enviar") {
            //function thas send data to db desde viewmodel
            viewmodel.sendToDbEsp()
        }
    }
}