package com.whgarcia.mtodobiseccin.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextWithSize(label : String, size : TextUnit, modifier: Modifier, fontWeight: FontWeight = FontWeight.Normal) {
    Text(text = label, fontSize = size, modifier = modifier, fontWeight = fontWeight)
}

@Composable
fun BoldText(label : String, size : TextUnit) {
    Text(text = label, fontSize = size ,fontWeight = FontWeight.Bold)
}