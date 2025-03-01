package com.example.users.presentation.ui.screens.register

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.State
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.Home
import com.example.core.navigation.SecondFormRegister
import com.example.users.R
import com.example.users.presentation.ui.components.ButtonPrimary
import com.example.users.presentation.ui.components.InputForm
import com.example.users.presentation.ui.components.TextPrimary
import com.example.users.presentation.viewmodels.register.FormRegisterVM
import es.dmoral.toasty.Toasty

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstRegister(
    viewModel: FormRegisterVM, navigate: (destination: Any) -> Unit
) {

    val inpName = viewModel.userName.value
    val inpLastName = viewModel.userLastName.value
    val inpAdress = viewModel.userAdress.value
    val inpPhone = viewModel.userPhone.value

    val context = LocalContext.current

    Column() {

        AnimatedVisibility(visible = true) {

            Column {
                TextPrimary("Rápido, seguro y confiable!", fontSize = 18.sp)
                InputForm(value = inpName,
                    VisualTransformation.Companion.None,
                    "Nombres",
                    { value ->
                        viewModel.updateName(value.toString())
                    })

                if (viewModel.nameError.value != null) {
                    Text(
                        viewModel.nameError.value ?: "", fontSize = 15.sp, color = Color.Red,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }





                InputForm(value = inpLastName,
                    VisualTransformation.Companion.None,
                    "Apellidos",
                    { value ->
                        viewModel.updateLastName(value.toString())
                    })

                if (viewModel.lastNameError.value != null) {
                    Text(
                        viewModel.lastNameError.value ?: "",
                        fontSize = 15.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }




                InputForm(value = inpAdress,
                    VisualTransformation.Companion.None,
                    "Dirección",
                    { value ->
                        viewModel.updateAddress(value.toString())
                    })

                if (viewModel.userAddressError.value != null) {
                    Text(
                        viewModel.userAddressError.value ?: "",
                        fontSize = 15.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }



                InputForm(
                    value = inpPhone,
                    VisualTransformation.Companion.None,
                    "Celular",
                    { value ->
                        viewModel.updatePhone(value.toString())
                    },
                    KeyboardOptions(keyboardType = KeyboardType.Phone),
                )

                if (viewModel.phoneError.value != null) {
                    Text(
                        viewModel.phoneError.value ?: "",
                        fontSize = 15.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 5.dp)
                    )
                }




                ButtonPrimary("Siguiente") {
                    if (!viewModel.validateForm()) {
                        Toasty.error(
                            context,
                            "Verifique los datos ingresados",
                            Toast.LENGTH_SHORT
                        ).apply {
                            setGravity(Gravity.TOP, 0, 100)
                            show()
                        }
                    } else {
                        navigate(SecondFormRegister)
                    }

                }
            }

        }
    }
}
