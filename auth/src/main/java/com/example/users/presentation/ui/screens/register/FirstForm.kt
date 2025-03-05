package com.example.users.presentation.ui.screens.register

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.components.Buttons.ButtonPrimary
import com.example.core.components.textfields.InputForm
import com.example.core.components.text.TextPrimary
import com.example.users.presentation.viewmodels.register.FormRegisterVM
import es.dmoral.toasty.Toasty

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FirstRegister(
    viewModel: FormRegisterVM, navigate: () -> Unit
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
                    VisualTransformation.None,
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
                    VisualTransformation.None,
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
                    VisualTransformation.None,
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
                    VisualTransformation.None,
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
                        navigate()
                    }

                }
            }

        }
    }
}
