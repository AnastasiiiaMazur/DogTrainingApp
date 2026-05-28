package com.cmps.dogtrainingapp.ui.screens.training

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.ui.screens.training.components.CourseCard
import androidx.compose.foundation.lazy.items


@Composable
fun TrainingRoute(
    viewModel: TrainingViewModel
) {
    val state = viewModel.uiState

    TrainingScreen(
        state = state
    )
}

@Composable
fun TrainingScreen(
    state: TrainingUiState
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 50.dp,
            bottom = 50.dp,
            start = 20.dp,
            end = 20.dp
        )
    ) {
        items(state.courses) { course ->

            CourseCard(
                course,
                onClick = {}
            )

        }
    }
}