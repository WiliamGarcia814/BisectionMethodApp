package com.whgarcia.mtodobiseccin.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

// Función composable que representa una entrada de valores de texto
@Composable
fun OutlineTextField(value : String, onValueChange : (String) -> Unit, label: String){
    OutlinedTextField(
        value = value ,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}

// Función composable que representa una entrada de valores númericos
@Composable
fun OutlineNumberField(value : String, onValueChange : (String) -> Unit, label: String, modifier: Modifier){
    OutlinedTextField(
        value = value ,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
    )
}