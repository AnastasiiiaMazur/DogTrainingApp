package com.cmps.dogtrainingapp.ui.screens.training

import com.cmps.dogtrainingapp.data.model.Course

data class TrainingUiState (
    val courses: List<Course> = emptyList()
)