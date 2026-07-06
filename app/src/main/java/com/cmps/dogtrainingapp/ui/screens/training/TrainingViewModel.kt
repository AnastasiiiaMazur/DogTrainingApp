package com.cmps.dogtrainingapp.ui.screens.training

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cmps.dogtrainingapp.data.repository.TrainingRepository

class TrainingViewModel(
    private val trainingRepo: TrainingRepository
): ViewModel() {

    var uiState by mutableStateOf(TrainingUiState())
        private set

    init {
        loadCourses()
    }

    private fun loadCourses() {
        uiState = uiState.copy(
            courses = trainingRepo.getCourses()
        )
    }

    fun onCourseClicked() {

    }

}