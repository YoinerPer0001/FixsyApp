package com.example.users.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextPrimary(text:String, color:Color = Color.Black, fontSize : TextUnit = 25.sp){
    Text(
        text,
        textAlign = TextAlign.Center,
        fontSize = fontSize,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.SansSerif,
        color = color,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    )
}