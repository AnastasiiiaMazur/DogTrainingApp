package com.cmps.dogtrainingapp.ui.screens.training.lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cmps.dogtrainingapp.data.repository.TrainingRepository

class LessonViewModel(
    private val trainingRepo: TrainingRepository
): ViewModel() {

    var uiState by mutableStateOf(LessonUiState())
        private set

    fun loadLesson(
        courseId: String,
        lessonId: String
    ) {
        val lesson = trainingRepo.getLesson(
            courseId = courseId,
            lessonId = lessonId
        )

        uiState = uiState.copy(
            lesson = lesson
        )
    }
}