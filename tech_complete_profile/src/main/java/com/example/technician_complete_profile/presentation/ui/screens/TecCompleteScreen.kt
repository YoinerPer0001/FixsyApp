package com.example.technician_complete_profile.presentation.ui.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.components.text.TextPrimary
import com.example.core.ui.theme.GrayBackInputs
import com.example.core.ui.theme.SecondButtonColor

import com.example.technician_complete_profile.presentation.viewmodels.FormCompletePerfilVM
import es.dmoral.toasty.Toasty

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TecCompleteScreen(viewModelForm: FormCompletePerfilVM, navigate: (destination: Any) -> Unit) {

    val formNumber by viewModelForm.formNumber.collectAsState(initial = 4)
    val especialties by viewModelForm.especialties.collectAsState()

    val context = LocalContext.current
    val techState by viewModelForm.techState.collectAsState(initial = TechState.Idle)


    LaunchedEffect(Unit) {
        viewModelForm.userDataVerify { destination ->
            navigate(destination)
        }
        viewModelForm.getSpecialties()
    }

    LaunchedEffect(techState) {
        when (val tech = techState) {
            is TechState.Success -> {

            }

            is TechState.Error -> {
                val errorMessage = (techState as TechState.Error).message
                Toasty.error(context, errorMessage, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }

    }


    var formOne by rememberSaveable { mutableStateOf(false) }
    var formTwo by rememberSaveable { mutableStateOf(false) }
    var formThree by rememberSaveable { mutableStateOf(false) }



    when (formNumber) {
        1 -> {
            formOne = true
            formTwo = false
            formThree = false
        }

        2 -> {
            formOne = false
            formTwo = true
            formThree = false
        }

        3 -> {
            formOne = false
            formTwo = false
            formThree = true
        }

        else -> {}
    }


    val painter = painterResource(com.example.core.R.drawable.head_logo)

    if (formNumber != 4) {
        Scaffold(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.systemBars),
            topBar = {
                NavigationBar(modifier = Modifier.height(100.dp), containerColor = Color.White) {
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .width(50.dp)
                                .height(50.dp),
                            painter = painter,
                            contentDescription = "logo head",
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            "Completa tu perfil",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )

                    }

                }
            }
        ) {


            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.White),
                Arrangement.Center,
                Alignment.CenterHorizontally
            ) {


                Box(
                    modifier = Modifier
                        .weight(0.2f)
                        .background(Color.White)
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {


                }

//            Text(numberForm.toString(), fontSize = 25.sp)


                //data
                AnimatedVisibility(modifier = Modifier.weight(0.7f), visible = formOne) {
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxSize()
                    ) {
                        FirstFormComplete(viewModelForm) {
                            navigate(it)
                        }
                    }
                }

                AnimatedVisibility(modifier = Modifier.weight(0.7f), visible = formTwo) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .background(Color.White)
                            .fillMaxSize()
                    ) {
                        SecondForm()
                    }
                }

                AnimatedVisibility(modifier = Modifier.weight(0.7f), visible = formThree) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .background(Color.White)
                            .fillMaxSize()
                    ) {
                        ThirdForm(viewModelForm, especialties)
                    }
                }
            }


        }
    }


}