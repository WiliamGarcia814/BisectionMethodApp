package com.whgarcia.mtodobiseccin.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogComponent(
    title: String,
    message: String,
    confirmText: String,
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
){
    AlertDialog(
        onDismissRequest = onDismissClick,
        title = { Text(text = title) },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = { onConfirmClick()}) {
                Text(text = confirmText)
            }
        }
    )
}