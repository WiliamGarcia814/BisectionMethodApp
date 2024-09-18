package com.whgarcia.mtodobiseccin.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.whgarcia.mtodobiseccin.component.AlertDialogComponent
import com.whgarcia.mtodobiseccin.component.OutlineNumberField
import com.whgarcia.mtodobiseccin.component.OutlineTextField
import com.whgarcia.mtodobiseccin.component.RadioButtonComponent
import com.whgarcia.mtodobiseccin.component.SimpleButton
import com.whgarcia.mtodobiseccin.component.SpaceH
import com.whgarcia.mtodobiseccin.component.SpaceW
import com.whgarcia.mtodobiseccin.component.TextWithSize
import com.whgarcia.mtodobiseccin.viewmodel.BisectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel: BisectionViewModel){
    // Obtener el estado del ViewModel para determinar si se debe mostrar el bottom sheet
    val showBottomSheet = viewModel.showBottomSheet
    // Crear y recordar el estado del bottom sheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    // Columna que organiza los elementos verticalmente
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Obtener el estado actual del ViewModel
        val state = viewModel.state

        // Campo de texto para la funció
        OutlineTextField(
            value = state.function,
            onValueChange = { viewModel.onValue(it, "function") },
            label = "Function"
        )

        SpaceH()

        // Fila que contiene dos campos numéricos (x0 y x1)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Primer campo numérico para el valor inicial x0
            OutlineNumberField(
                value = state.x0,
                onValueChange = {viewModel.onValue(it, "x0")},
                label = "Initial value x0",
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(1f)
            )

            SpaceW()

            // Segundo campo numérico para el valor inicial x1
            OutlineNumberField(
                value = state.x1,
                onValueChange = {viewModel.onValue(it, "x1")},
                label = "Initial value x1",
                modifier = Modifier
                    .padding(end = 30.dp)
                    .weight(1f)
            )
        }

        SpaceH()

        // Campo numérico para la tolerancia deseada
        OutlineNumberField(
            value = state.tolerance,
            onValueChange = {viewModel.onValue(it, "tolerance")},
            label = "Desired tolerance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        SpaceH(10.dp)

        // Texto para el tipo de tolerancia
        TextWithSize(
            label = "Tolerance type",
            size = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        // Componente para los botones de radio para seleccionar el tipo de tolerancia
        RadioButtonComponent(
            selectedOption = state.type_tolerance,
            onOptionSelected = { viewModel.onValue(it.toString(), "type_tolerance") }
        )

        SpaceH()

        // Texto para la precisión del cálculo
        TextWithSize(
            label = "Calculation precision",
            size = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Texto para la cantidad de dígitos después del punto decimal
            TextWithSize(
                label = "Digits after the decimal ponit: ",
                size = 14.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(3f)
            )

            SpaceW()

            // Texto que muestra la precisión actual
            TextWithSize(
                label = state.precision,
                size = 14.sp,
                modifier = Modifier
                    .padding(end = 30.dp)
                    .weight(1f)
            )
        }

        SpaceH()

        // Deslizador para ajustar la precisión
        Slider(
            value = state.precision.toFloat(),
            onValueChange = { viewModel.onValue(it.toInt().toString(), "precision") },
            valueRange = 0f..20f,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
        )

        SpaceH(20.dp)

        // Botón para calcular
        SimpleButton(
            text = "CALCULATE"
        ) {
            viewModel.onCalculate() // Acción al hacer clic en el botón
        }

        // Mostrar el bottom sheet si es necesario
        if (showBottomSheet){
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { viewModel.showBottomSheet(false) }
            ) {
                Column {
                    TextWithSize(
                        label = "Result of bisection:",
                        size = 18.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    )
                    TextWithSize(
                        label = state.bisectioResult.toString(),
                        size = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Mostrar un diálogo de alerta si es necesario
        if (state.showAlert){
            AlertDialogComponent(
                title = "Notification",
                message = state.alertMessage,
                confirmText = "Ok",
                onConfirmClick = { viewModel.dismissAlert() }) {
            }
        }
    }
}

