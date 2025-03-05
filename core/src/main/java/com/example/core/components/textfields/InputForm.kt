package com.example.core.components.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.GrayBackInputs

@Composable
fun InputForm(value:String = "", transformation: VisualTransformation, label: String, response: (value: Any) -> Unit, typeData: KeyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Text)) {
    var newValue by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = value,
        onValueChange = {
            newValue = it
            response(newValue)
        },
        label = { Text(label) },
        keyboardOptions = typeData,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = GrayBackInputs,
            focusedContainerColor = GrayBackInputs,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedLabelColor = Color.Gray,
            focusedLabelColor = Color.Gray
        ),
        visualTransformation = transformation,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    )
}