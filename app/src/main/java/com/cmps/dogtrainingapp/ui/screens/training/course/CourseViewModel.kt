package com.cmps.dogtrainingapp.ui.screens.training.course

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class CourseViewModel(
    private val trainingRepo: TrainingRepository
): ViewModel() {

    var uiState by mutableStateOf(CourseUiState())
        private set

//    fun loadCourse(courseId: String) {
//        val course = trainingRepo.getCourses()
//            .firstOrNull { it.id == courseId }
//
//        uiState = uiState.copy(
//            course = course
//        )
//    }

    fun loadCourse(courseId: String) {
        viewModelScope.launch {
            val course = trainingRepo.getCourse(courseId)
            val completedLessonIds = trainingRepo.getCompletedLessonIds(courseId)

            uiState = uiState.copy(
                course = course,
                completedLessonIds = completedLessonIds
            )
        }
    }
}