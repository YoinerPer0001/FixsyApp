package com.example.users.presentation.ui.screens.register

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
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
import com.example.core.data.ClientRegisterRequest
import com.example.core.navigation.Home
import com.example.core.navigation.Login
import com.example.core.ui.theme.SecondButtonColor
import com.example.core.utils.UserType
import com.example.core.utils.idTypes
import com.example.core.validators.IsValidName
import com.example.users.R
import com.example.users.presentation.ui.components.ButtonPrimary
import com.example.users.presentation.ui.components.DropDown
import com.example.users.presentation.ui.components.InputForm
import com.example.users.presentation.ui.components.TextPrimary
import com.example.users.presentation.viewmodels.register.ClientRegisterViewModel
import com.example.users.presentation.viewmodels.register.FormRegisterVM
import com.example.users.presentation.viewmodels.register.RegisterState
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(
    viewmodel: ClientRegisterViewModel,
    registerVM: FormRegisterVM,
    navigate: (route: Any) -> Unit
) {

    val context = LocalContext.current

    val registerState by viewmodel._registerState.collectAsState(RegisterState.Idle)

    LaunchedEffect(registerState) {
        when (registerState) {

            is RegisterState.Success -> {
                Toasty.success(context, "Registrado con exito", Toast.LENGTH_SHORT).show()
                delay(100)
                registerVM.clearData()
                navigate(Login)
            }

            is RegisterState.Error -> {
                val errorMessage = (registerState as RegisterState.Error).message;
                Toasty.error(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {

            }
        }
    }

    var contador by remember { mutableStateOf<Int>(1) }

    var firstForm by remember { mutableStateOf(false) }
    var secondForm by remember { mutableStateOf(false) }
    var thirdForm by remember { mutableStateOf(false) }

    val idlist = mutableListOf<String>()
    for (type in idTypes.entries) {
        idlist.add(type.toString())
    }
    var painter = painterResource(id = R.drawable.logo_no_fondo)

    LaunchedEffect(contador) {
        when (contador) {
            0 -> {
                navigate(Login)
            }

            1 -> {
                firstForm = true
                secondForm = false
                thirdForm = false

            }

            2 -> {
                firstForm = false
                secondForm = true
                thirdForm = false

            }

            3 -> {
                firstForm = false
                secondForm = false
                thirdForm = true
            }

            4 -> {
                firstForm = false
                secondForm = false
            }


            else -> {}
        }
    }


    Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
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
            AnimatedVisibility(visible = firstForm) {
                FirstRegister(registerVM) {
                    //navigate to do click in next button
                    contador +=1
                }
            }

            AnimatedVisibility(visible = secondForm) {
                SecondForm (registerVM) {
                    //navigate to do click in next button
                    contador +=1
                }
            }

            AnimatedVisibility(visible = thirdForm) {
                ThirdForm (registerVM, viewmodel)
            }


            ButtonPrimary("Atrás", SecondButtonColor) {
                if(contador > 0) {
                    contador -= 1
                }
            }


        }
    }
}