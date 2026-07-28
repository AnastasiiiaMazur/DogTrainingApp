package com.cmps.dogtrainingapp.ui.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import com.cmps.dogtrainingapp.data.repository.HealthEventRepository
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class DashboardViewModel(
    private val recsRepository: DailyRecommendationsRepository,
    private val petRepo: PetRepository,
    private val trainingRepository: TrainingRepository,
    private val healthEventRepository: HealthEventRepository
): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    init {
        loadRecs()
        loadCurrentPet()
        observeCourseProgress()
        refreshRepeatingEvents()
        observeUpcomingEvents()
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

    private fun refreshRepeatingEvents() {
        viewModelScope.launch {
            runCatching {
                healthEventRepository
                    .refreshRepeatingEventsIfNeeded()
            }.onFailure { exception ->
                uiState = uiState.copy(
                    errorMessage =
                        exception.message
                            ?: "Unable to refresh events."
                )
            }
        }
    }

    private fun observeUpcomingEvents() {
        viewModelScope.launch {
            healthEventRepository
                .getEventsForPet()
                .collect { events ->

                    val today = LocalDate.now()

                    val startOfWeek =
                        today.with(DayOfWeek.MONDAY)

                    val endOfWeek =
                        startOfWeek.plusDays(6)

                    val weeklyEvents = events
                        .filter { event ->
                            !event.isCompleted &&
                                    event.date in startOfWeek..endOfWeek
                        }
                        .sortedWith(
                            compareBy<HealthEventEntity> {
                                it.date
                            }.thenBy {
                                it.time
                            }
                        )

                    uiState = uiState.copy(
                        upcomingEvents = weeklyEvents,
                        errorMessage = null
                    )
                }
        }
    }

}