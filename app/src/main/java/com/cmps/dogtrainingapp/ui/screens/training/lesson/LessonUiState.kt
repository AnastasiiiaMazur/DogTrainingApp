package com.cmps.dogtrainingapp.ui.screens.training.lesson

import com.cmps.dogtrainingapp.data.model.course.Lesson

data class LessonUiState(
    val lesson: Lesson? = null,
    val isCompleted: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)