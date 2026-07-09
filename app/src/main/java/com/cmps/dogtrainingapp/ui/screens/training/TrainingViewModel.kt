package com.cmps.dogtrainingapp.ui.screens.training

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class TrainingViewModel(
    private val trainingRepo: TrainingRepository
) : ViewModel() {

    var uiState by mutableStateOf(TrainingUiState())
        private set

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch {
            val courses = trainingRepo.getCourses()

            trainingRepo.getCompletedLessonCounts()
                .collect { completedMap ->
                    uiState = uiState.copy(
                        courses = courses,
                        completedLessonsByCourse = completedMap
                    )
                }
        }
    }
}