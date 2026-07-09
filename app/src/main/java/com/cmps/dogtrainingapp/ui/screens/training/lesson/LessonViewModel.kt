package com.cmps.dogtrainingapp.ui.screens.training.lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class LessonViewModel(
    private val trainingRepo: TrainingRepository
): ViewModel() {

    var uiState by mutableStateOf(LessonUiState())
        private set

    private var currentCourseId: String? = null
    private var currentLessonId: String? = null

    fun loadLesson(
        courseId: String,
        lessonId: String
    ) {
        currentCourseId = courseId
        currentLessonId = lessonId

        viewModelScope.launch {

            val lesson = trainingRepo.getLesson(courseId, lessonId)
            val completed = trainingRepo.isLessonCompleted(courseId, lessonId)

            uiState = uiState.copy(
                lesson = lesson,
                isCompleted = completed
            )
        }
    }

    fun markLessonComplete() {
        val courseId = currentCourseId ?: return
        val lessonId = currentLessonId ?: return

        viewModelScope.launch {
            trainingRepo.markLessonComplete(
                courseId = courseId,
                lessonId = lessonId
            )

            uiState = uiState.copy(
                isCompleted = true
            )
        }
    }
}