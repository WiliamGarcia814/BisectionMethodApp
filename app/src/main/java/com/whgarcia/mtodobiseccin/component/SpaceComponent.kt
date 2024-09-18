package com.whgarcia.mtodobiseccin.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Función composable que representa un espacio vertical (espaciador)
@Composable
fun SpaceH(size : Dp = 5.dp){
    Spacer(modifier = Modifier.height(size))
}

// Función composable que representa un espacio horizontal (espaciador)
@Composable
fun SpaceW(size : Dp = 5.dp){
    Spacer(modifier = Modifier.width(size))
}