package com.whgarcia.mtodobiseccin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.whgarcia.mtodobiseccin.component.BoldText
import com.whgarcia.mtodobiseccin.ui.theme.MétodoBisecciónTheme
import com.whgarcia.mtodobiseccin.view.ContentHomeView
import com.whgarcia.mtodobiseccin.viewmodel.BisectionViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : BisectionViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            MétodoBisecciónTheme {
                Scaffold(
                    topBar = { TopAppBar(
                        title = { BoldText(label = "Bisection method", size = 30.sp) },
                    )},
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ContentHomeView(paddingValues = innerPadding, viewModel)
                }
            }
        }
    }
}