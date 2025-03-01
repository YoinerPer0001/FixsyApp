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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.Login
import com.example.core.navigation.SecondFormRegister
import com.example.core.utils.idTypes
import com.example.users.presentation.ui.components.ButtonPrimary
import com.example.users.presentation.ui.components.DropDown
import com.example.users.presentation.ui.components.InputForm
import com.example.users.presentation.ui.components.TextPrimary
import com.example.users.presentation.viewmodels.register.FormRegisterVM
import es.dmoral.toasty.Toasty

@Composable
fun SecondForm(
    viewModel: FormRegisterVM,
    navigate: () -> Unit
) {
    val idlist = mutableListOf<String>()
    for (type in idTypes.entries) {
        idlist.add(type.toString())
    }

    val context = LocalContext.current

    val inpId = viewModel.userId.value

    Column {
        TextPrimary("Servicios a tu medida", fontSize = 18.sp)
        DropDown("Tipo de documento", idlist) { value ->
            viewModel.updateIdType(value)
        }

        InputForm(
            value = inpId,
            VisualTransformation.Companion.None,
            "Numero",
            { value ->
                viewModel.updateUserId(value.toString())
            },
            KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        if (viewModel.userIdError.value != null) {
            Text(
                viewModel.userIdError.value ?: "",
                fontSize = 15.sp,
                color = Color.Red,
                modifier = Modifier.padding(top = 5.dp)
            )
        }

        ButtonPrimary("Siguiente") {
            if (!viewModel.validateSecondForm()) {
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