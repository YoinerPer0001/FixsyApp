package com.example.users.presentation.ui.screens.register

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.data.ClientRegisterRequest
import com.example.core.components.Buttons.ButtonPrimary
import com.example.core.components.textfields.DropDown
import com.example.core.components.textfields.InputForm
import com.example.core.components.text.TextPrimary
import com.example.users.presentation.viewmodels.register.ClientRegisterViewModel
import com.example.users.presentation.viewmodels.register.FormRegisterVM
import es.dmoral.toasty.Toasty

@Composable
fun ThirdForm(
    viewModel: FormRegisterVM,
    registerVM : ClientRegisterViewModel
){

    val inpEmail = viewModel.userEmail.value
    val inpPassFirst = viewModel.firstPass.value
    val inpPassSecond = viewModel.secondPass.value

    val context = LocalContext.current


    Column {
        TextPrimary("Reparaciones sin complicaciones.", fontSize = 18.sp)

        InputForm(value = inpEmail, VisualTransformation.None, "Correo electronico", { value ->
            viewModel.updateEmail(value.toString())
        }, KeyboardOptions(keyboardType = KeyboardType.Email))

        if (viewModel.userEmailError.value != null) {
            Text(
                viewModel.userEmailError.value ?: "",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 5.dp)
            )
        }


        InputForm(value = inpPassFirst, PasswordVisualTransformation(), "Contraseña", { value ->
            viewModel.updateFirstPass(value.toString())
        })

        if (viewModel.firstPassError.value != null) {
            Text(
                viewModel.firstPassError.value ?: "",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        InputForm(value = inpPassSecond, PasswordVisualTransformation(), "Repetir contraseña", { value ->
            viewModel.updateSecondPass(value.toString())
        })

        if (viewModel.secondPassError.value != null) {
            Text(
                viewModel.secondPassError.value ?: "",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        DropDown("Registrarse como:", listOf<String>("Técnico", "Cliente"), response = { value ->
            viewModel.updateUserType(value)
        })

        ButtonPrimary("Registrarme") {
            if (!viewModel.validateThirdForm()) {
                Toasty.error(
                    context,
                    "Verifique los datos ingresados",
                    Toast.LENGTH_SHORT
                ).apply {
                    setGravity(Gravity.TOP, 0, 100)
                    show()
                }
            } else {
                val client = ClientRegisterRequest(
                    name = (viewModel.userName.value.trim() + " " + viewModel.userLastName.value.trim()),
                    email = viewModel.userEmail.value.trim(),
                    id_number = viewModel.userId.value.toLong(),
                    phone = viewModel.userPhone.value.toLong(),
                    password = viewModel.secondPass.value.trim(),
                    address = viewModel.userAdress.value.trim(),
                    id_num_type = viewModel.idType.value.trim(),
                    type = viewModel.userType.value.trim()
                )
                registerVM.onRegister(client)
            }

        }

    }
}