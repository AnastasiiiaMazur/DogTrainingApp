package com.cmps.dogtrainingapp.ui.screens.health

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cmps.dogtrainingapp.ui.theme.Red

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventRoute(viewModel: HealthEventViewModel) {
    val state = viewModel.uiState

    HealthEventScreen(
        state = state
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HealthEventScreen(
    state: HealthEventUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Red)
    ) { }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun PreviewScreen() {
    HealthEventScreen(
        state = HealthEventUiState(

        )
    )
}