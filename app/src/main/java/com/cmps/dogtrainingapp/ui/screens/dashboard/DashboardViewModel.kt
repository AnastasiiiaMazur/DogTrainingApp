package com.cmps.dogtrainingapp.ui.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val recsRepository: DailyRecommendationsRepository,
    private val petRepo: PetRepository,
    private val trainingRepository: TrainingRepository
): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    init {
        loadRecs()
        loadCurrentPet()
        observeCourseProgress()
    }

    private fun loadRecs() {
        val recs = recsRepository.getRecommendations()

        uiState = uiState.copy(
            dailyRecs = recs
        )
    }

    private fun loadCurrentPet() {
        viewModelScope.launch {
            petRepo.getCurrentPet().collect { pet ->
                uiState = uiState.copy(
                    currentPet = pet
                )
            }
        }
    }

    private fun observeCourseProgress() {
        viewModelScope.launch {
            trainingRepository.getCompletedLessonCounts()
                .collect { completedByCourse ->

                    val courses = trainingRepository.getCourses()

                    val course = courses.firstOrNull { c ->
                        completedByCourse.getOrDefault(c.id, 0) > 0
                    } ?: courses.firstOrNull()

                    uiState = uiState.copy(
                        courseProgress = course,
                        completedLessons = course?.let {
                            completedByCourse.getOrDefault(it.id, 0)
                        } ?: 0
                    )
                }
        }
    }


}