package com.example.users.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core.ui.theme.GrayBackInputs

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDown(label:String, list: List<String>, response: (String)-> Unit) {

    var rol by rememberSaveable { mutableStateOf(list[0]) }

    LaunchedEffect(Unit) {
        response(rol)
    }

    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }) {
            TextField(modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = GrayBackInputs,
                    focusedContainerColor = GrayBackInputs,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Gray,
                    focusedLabelColor = Color.Gray
                ),
                label = { Text(label) },
                value = rol,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) })

            ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                list.forEachIndexed() { index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text) },
                        onClick = {
                            rol = list[index]
                            isExpanded = !isExpanded
                            response(rol)
                        }
                    )
                }
            }
        }


    }
}

