package com.cmps.dogtrainingapp.ui.screens.dashboard.recommendation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Red

@Composable
fun RecommendationRoute(
    recId: String?,
    viewModel: RecViewModel
) {

    LaunchedEffect(recId) {
        if (recId != null) {
            viewModel.loadRec(recId)
        }
    }

    val state = viewModel.uiState

    RecommendationScreen(
        state = state
    )
}

@Composable
fun RecommendationScreen(
    state: RecUiState
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Red)
    ) {
        Text(
            text = state.rec?.title ?: "test",
            color = Black,
            modifier = Modifier.padding(top = 60.dp)
        )
    }
}