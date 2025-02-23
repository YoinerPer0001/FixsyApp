package com.example.users.presentation.ui.screens.login

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.navigation.Home
import com.example.core.navigation.Register
import com.example.core.ui.theme.ButtonBackColor
import com.example.core.ui.theme.GrayBackInputs
import com.example.users.R
import com.example.users.domain.models.BasicUserBM
import com.example.users.presentation.ui.components.ButtonPrimary
import com.example.users.presentation.ui.components.DropDown
import com.example.users.presentation.ui.components.InputForm
import com.example.users.presentation.ui.components.TextPrimary
import com.example.users.presentation.viewmodels.LoginViewModel
import java.io.Serializable


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(loginViewModel: LoginViewModel, navigate: (destination: Any) -> Unit) {

    val loginState by loginViewModel.loginState.collectAsStateWithLifecycle(initialValue = LoginState.Idle)

    val context = LocalContext.current
    var rol by remember  { mutableStateOf("") }

    val list = listOf("Cliente", "Técnico")

    var email_value by remember { mutableStateOf("") }
    var password_value by remember { mutableStateOf("") }

    var painter = painterResource(id = R.drawable.logo_no_fondo)



    Scaffold(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars)) {

        LaunchedEffect(loginState) {
            when (loginState) {
                is LoginState.Success -> {
                    navigate(Home)
                }

                is LoginState.Error -> {
                    val errorMessage = (loginState as LoginState.Error).message
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }

                is LoginState.ErrorEmail -> {
                    val errorMessage = (loginState as LoginState.ErrorEmail).message
                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }

        }

        when (loginState) {
            is LoginState.Loading -> CircularProgressIndicator()

            is LoginState.Error -> Text(
                text = (loginState as LoginState.Error).message,
                color = Color.Red
            )

            else -> {}
        }

        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
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
            Text(
                "Bienvenido a FixSy !",
                textAlign = TextAlign.Center,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
            DropDown("Ingresar como:", list, response = { rol = it })

            InputForm(email_value,
                VisualTransformation.None,
                "Correo electronico",
                { value -> email_value = value.toString() })



            InputForm(password_value,PasswordVisualTransformation(), "Contraseña", { value ->
                password_value = value.toString()
            })

            ButtonPrimary("Iniciar sesión") {
                loginViewModel.onLogin(email_value, password_value, rol)
            }
            TextPrimary("Encuentra expertos o conviértete en uno.", fontSize = 15.sp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

                Text(
                    "Todo empieza aquí.",
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontStyle = FontStyle.Normal,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier.padding(top = 10.dp, end = 5.dp)
                )
                Text(
                    "Regístrate",
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 5.dp)
                        .clickable {
                            navigate(
                                Register
                            )
                        },
                    color = ButtonBackColor,
                )
            }
        }
    }


}