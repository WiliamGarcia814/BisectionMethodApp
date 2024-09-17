package com.whgarcia.mtodobiseccin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleButton(text: String, color: Color = MaterialTheme.colorScheme.primary, onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick ,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color,
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    ) {
        TextWithSize(label = text, size = 16.sp, modifier = Modifier.padding(5.dp))
    }
}

@Composable
fun RadioButtonComponent(selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    // Mapa que asocia las opciones de texto con valores numéricos
    val radioOptions = mapOf(
        0 to "Endpoint convergence",
        1 to "Function convergence"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Itera sobre el mapa de opciones
        radioOptions.forEach { (value, text) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (value == selectedOption),
                        onClick = { onOptionSelected(value) } // Devuelve el valor numérico
                    )
                    .padding(horizontal = 30.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (value == selectedOption),
                    onClick = { onOptionSelected(value) }
                )
                TextWithSize(label = text, size = 14.sp, modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}