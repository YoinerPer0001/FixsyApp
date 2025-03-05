package com.example.technician_complete_profile.presentation.ui.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.rememberAsyncImagePainter
import com.example.core.components.Buttons.ButtonPrimary
import com.example.core.components.text.TextPrimary
import com.example.core.navigation.TechnicianHome
import com.example.core.ui.theme.ButtonBackColor
import com.example.core.ui.theme.SecondButtonColor
import com.example.technician_complete_profile.presentation.viewmodels.FormCompletePerfilVM
import es.dmoral.toasty.Toasty

@Composable
fun FirstFormComplete(viewModel:FormCompletePerfilVM, navigate: (destination: Any)-> Unit){

    var painter by remember { mutableStateOf<Uri?>(null) }

    val photoCloud by viewModel.url_perfil.collectAsState()

    val updatedPhoto by viewModel.photoUpdated.collectAsState()

    val context = LocalContext.current

    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->

        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            painter = uri
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    LaunchedEffect(photoCloud) {
        photoCloud?.let {
            viewModel.updateImagePerfil(it){
                navigate(TechnicianHome)
            }
        }
    }

    LaunchedEffect(updatedPhoto) {
        if (updatedPhoto == false) {
           Toasty.error(context, "Error al guardar, intente nuevamente", Toast.LENGTH_SHORT).show()
        }else if(updatedPhoto != null){
            Toasty.success(context, "Foto guardada", Toast.LENGTH_SHORT).show()
        }
    }



    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        Box(Modifier.padding(bottom = 50.dp, top = 40.dp)){

            Box(modifier = Modifier
                .width(270.dp)
                .height(270.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(100))
                .background(brush = Brush.linearGradient(
                    colors =  listOf(ButtonBackColor, SecondButtonColor),
                    start = Offset(100f, 100f), // Punto inicial
                    end = Offset(105f, 300f) // Punto final (diagonal)
                ))){
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(7.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(100))
                    .background(Color.White)){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .clip(
                                RoundedCornerShape(100)
                            )
                            .background(Color.DarkGray)
                    ) {
                        painter?.let {
                            Image(
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = "Imagen seleccionada",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.matchParentSize()
                            )
                        }


                    }


                }
                

            }

            FloatingActionButton( containerColor = Color.White, shape = ShapeDefaults.ExtraLarge, onClick = {
                pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
            }, modifier = Modifier
                .width(100.dp)
                .align(Alignment.BottomCenter)
                .padding(10.dp)
                .offset(y = 30.dp)
                .zIndex(1f), )
            {
                Icon(
                    imageVector = Icons.Outlined.PhotoCamera,
                    contentDescription = "abrir galeria"
                )
            }
        }

        TextPrimary("Agrega tu foto y destaca ✨", fontSize = 20.sp)


        ButtonPrimary("Enviar") {
            //sent to cloud
            if(painter != null){
                viewModel.uploadImage(painter!!)
            }else{
                Toasty.warning(context, "Elija una imagen", Toast.LENGTH_SHORT).show()
            }
        }

    }
}