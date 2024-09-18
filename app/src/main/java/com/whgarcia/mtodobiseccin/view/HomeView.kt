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
    val showBottomSheet = viewModel.showBottomSheet
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel.state

        OutlineTextField(
            value = state.function,
            onValueChange = { viewModel.onValue(it, "function") },
            label = "Function"
        )

        SpaceH()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlineNumberField(
                value = state.x0,
                onValueChange = {viewModel.onValue(it, "x0")},
                label = "Initial value x0",
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(1f)
            )
            SpaceW()
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

        OutlineNumberField(
            value = state.tolerance,
            onValueChange = {viewModel.onValue(it, "tolerance")},
            label = "Desired tolerance",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        SpaceH(10.dp)

        TextWithSize(
            label = "Tolerance type",
            size = 12.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        RadioButtonComponent(
            selectedOption = state.type_tolerance,
            onOptionSelected = { viewModel.onValue(it.toString(), "type_tolerance") }
        )

        SpaceH()

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
            TextWithSize(
                label = "Digits after the decimal ponit: ",
                size = 14.sp,
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(3f)
            )

            SpaceW()

            TextWithSize(
                label = state.precision,
                size = 14.sp,
                modifier = Modifier
                    .padding(end = 30.dp)
                    .weight(1f)
            )
        }

        SpaceH()

        Slider(
            value = state.precision.toFloat(),
            onValueChange = { viewModel.onValue(it.toInt().toString(), "precision") },
            valueRange = 0f..20f,
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
        )

        SpaceH(20.dp)

        SimpleButton(
            text = "CALCULATE"
        ) {
            viewModel.onCalculate()
        }

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

