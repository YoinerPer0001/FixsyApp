package com.example.users.presentation.ui.screens.register

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.Home
import com.example.core.navigation.Login
import com.example.core.ui.theme.SecondButtonColor
import com.example.core.utils.UserType
import com.example.core.utils.idTypes
import com.example.users.R
import com.example.users.presentation.ui.components.ButtonPrimary
import com.example.users.presentation.ui.components.DropDown
import com.example.users.presentation.ui.components.InputForm
import com.example.users.presentation.ui.components.TextPrimary

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navigate: (route: Any)-> Unit) {

    var contador by remember { mutableStateOf<Int>(1) }

    var docVisible by remember { mutableStateOf(false) }
    var idVisible by remember { mutableStateOf(false) }
    var emailPassVisible by remember { mutableStateOf(false) }
    var typeVisible by remember { mutableStateOf(false) }
    var btnNextVisible by remember { mutableStateOf<Boolean>(true) }
    var btnRegisterVisible by remember { mutableStateOf(false) }

    var inpName  by rememberSaveable  { mutableStateOf("") }
    var inpLastName  by rememberSaveable  { mutableStateOf("") }
    var inpAdress  by rememberSaveable  { mutableStateOf("") }
    var inpTypeID  by rememberSaveable  { mutableStateOf("") }
    var inpID  by rememberSaveable  { mutableStateOf("") }
    var inpEmail  by rememberSaveable  { mutableStateOf("") }
    var inpPassFirst  by rememberSaveable  { mutableStateOf("") }
    var inpPassSecond  by rememberSaveable  { mutableStateOf("") }
    var inpTypeUser by rememberSaveable  { mutableStateOf("") }


    val idlist = mutableListOf<String>()
    for (type in idTypes.entries) {
        idlist.add(type.toString())
    }
    var painter = painterResource(id = R.drawable.logo_no_fondo)

    LaunchedEffect(contador) {
        when (contador){
            0->{
                navigate(Login)
            }
            1 -> {
                docVisible = true
                idVisible = false
                emailPassVisible = false
                typeVisible = false
                btnRegisterVisible = false
                btnNextVisible = true
            }

            2-> {
                docVisible = false
                idVisible = true
                emailPassVisible = false
                typeVisible = false
                btnRegisterVisible = false
                btnNextVisible = true
            }
            3->{
                docVisible = false
                idVisible = false
                emailPassVisible = true
                typeVisible = false
                btnRegisterVisible = false
                btnNextVisible = true
            }

            4-> {
                docVisible = false
                idVisible = false
                emailPassVisible = false
                typeVisible = true
                btnRegisterVisible = false
                btnNextVisible = true
            }


            else -> {}
        }
    }

    LaunchedEffect(inpTypeUser) {
        when(inpTypeUser){
            "Cliente" -> {
                btnRegisterVisible = true
                btnNextVisible = false
            }
            else -> {
                btnRegisterVisible = false
                btnNextVisible = true
            }
        }
    }

    Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 20.dp).verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
        ) {
            //image
            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clip(
                        RoundedCornerShape(50)
                    )
                    .background(Color.White)
                    .border(2.dp, Color.White, RoundedCornerShape(50))
            ) {
                Image(
                    painter = painter,
                    contentDescription = "logo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            //information


            AnimatedVisibility (visible = docVisible) {

                Column {
                    TextPrimary("Rápido, seguro y confiable!", fontSize = 18.sp)
                    InputForm(value = inpName, VisualTransformation.Companion.None, "Nombres", { value ->
                        inpName = value.toString()
                    })

                    InputForm(value = inpLastName, VisualTransformation.Companion.None, "Apellidos", { value ->
                        inpLastName = value.toString()
                    })

                    InputForm(value = inpAdress, VisualTransformation.Companion.None, "Dirección", { value ->
                        inpAdress = value.toString()
                    })
                }

            }

            AnimatedVisibility(visible = idVisible){

                Column {
                    TextPrimary("Servicios a tu medida", fontSize = 18.sp)
                    DropDown("Tipo de documento", idlist) { value ->
                        inpTypeID = value
                    }

                    InputForm(value = inpID.toString(), VisualTransformation.Companion.None, "Numero", { value ->
                        inpID = value.toString()
                    }, KeyboardOptions(keyboardType = KeyboardType.Number))
                }


            }


            AnimatedVisibility(visible = emailPassVisible){

                Column {
                    TextPrimary("Reparaciones sin complicaciones.", fontSize = 18.sp)

                    InputForm(value = inpEmail, VisualTransformation.Companion.None, "Correo electronico", { value ->
                        inpEmail = value.toString()
                    }, KeyboardOptions(keyboardType = KeyboardType.Email))


                    InputForm(value = inpPassFirst, PasswordVisualTransformation(), "Contraseña", { value ->
                        inpPassFirst = value.toString()
                    })

                    InputForm(value = inpPassSecond, PasswordVisualTransformation(), "Repetir contraseña", { value ->
                        inpPassSecond = value.toString()
                    })
                }

            }

           AnimatedVisibility(visible = typeVisible) {
               Column {
                   TextPrimary("Encuentra o brinda el servicio ideal", fontSize = 18.sp)
                   DropDown("Registrarse como:", listOf<String>("Tecnico", "Cliente"), response = { value ->
                       inpTypeUser = value
                   })
               }

           }


            AnimatedVisibility(visible = btnNextVisible){

                ButtonPrimary("Siguiente") {
                    if(contador < 4){
                        contador = contador + 1

                    }else{
                        navigate(Home)
                    }
                }
            }

            AnimatedVisibility(visible = btnRegisterVisible){
                ButtonPrimary("Registrarme!") {

                }
            }

            ButtonPrimary("Atrás", SecondButtonColor) {
                if(contador > 0) {
                    contador = contador - 1
                }
            }




        }
    }
}