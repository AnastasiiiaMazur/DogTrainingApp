package com.cmps.dogtrainingapp.ui.screens.health

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.HealthEventType
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import com.cmps.dogtrainingapp.data.repository.HealthEventRepository
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
//class HealthEventViewModel(
//    private val healthRepo: HealthEventRepository
//): ViewModel() {
//
//    var uiState by mutableStateOf(HealthEventUiState())
//        private set
//
//    init {
//        loadEvents()
//    }
//
//    private fun loadEvents() {
//        viewModelScope.launch {
//            healthRepo.getEventsForPet().collect { events ->
//
//                val now = LocalDateTime.now()
//
//                val today = LocalDate.now()
//                val startOfWeek = today.with(DayOfWeek.MONDAY)
//                val endOfWeek = startOfWeek.plusDays(6)
//
//                val overdue = events.count {
//                    !it.isCompleted && LocalDateTime.of(it.date, it.time).isBefore(now)
//                }
//
//                val upcoming = events.count {
//                    !it.isCompleted && LocalDateTime.of(it.date, it.time).isAfter(now)
//                }
//
//                val weeklyEvents = events.filter {
//                    it.date in startOfWeek..endOfWeek
//                }
//
//                val completed = events.count { it.isCompleted }
//
//                uiState = uiState.copy(
//                    healthEvents = events,
//                    weeklyEvents = weeklyEvents,
//
//                    totalEvents = events.size,
//                    upcomingEvents = upcoming,
//                    overdueEvents = overdue,
//                    completedEvents = completed
//                )
//            }
//        }
//    }
//
//    fun onCompleteClicked(event: HealthEventEntity) {
//        viewModelScope.launch {
//
//            val isNowCompleted = !event.isCompleted
//
//            val updatedEvent = event.copy(
//                isCompleted = isNowCompleted,
//                completedOn = if (isNowCompleted) {
//                    LocalDateTime.now()
//                } else {
//                    null
//                }
//            )
//
//            healthRepo.updateEvent(updatedEvent)
//        }
//    }
//}



class HealthEventViewModel(
    private val healthEventRepository: HealthEventRepository
) : ViewModel() {

    var uiState by mutableStateOf(
        HealthEventUiState()
    )
        private set

    init {
        refreshRepeatingEvents()
        observeEvents()
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

    private fun observeEvents() {
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
                            event.date in startOfWeek..endOfWeek &&
                                    !event.isCompleted
                        }
                        .sortedWith(
                            compareBy<HealthEventEntity> {
                                it.date
                            }.thenBy {
                                it.time
                            }
                        )

                    uiState = uiState.copy(
                        healthEvents = events,
                        weeklyEvents = weeklyEvents,

                        totalEvents = events.size,

                        upcomingEvents = events.count { event ->
                            !event.isCompleted &&
                                    !event.date.isBefore(today)
                        },

                        overdueEvents = events.count { event ->
                            !event.isCompleted &&
                                    event.date.isBefore(today)
                        },

                        completedEvents = events.count { event ->
                            event.isCompleted
                        },

                        errorMessage = null
                    )
                }
        }
    }

    fun onCompleteClicked(
        event: HealthEventEntity
    ) {

        if (!canComplete(event)) {
            uiState = uiState.copy(
                errorMessage = "You can only complete today's or overdue events."
            )
            return
        }

        viewModelScope.launch {
            runCatching {
                if (
                    event.repeat == RepeatInterval.NEVER &&
                    event.isCompleted
                ) {
                    healthEventRepository
                        .uncompleteEvent(event)
                } else {
                    healthEventRepository
                        .completeEvent(event)
                }
            }.onFailure { exception ->
                uiState = uiState.copy(
                    errorMessage =
                        exception.message
                            ?: "Unable to update event."
                )
            }
        }
    }

    private fun canComplete(event: HealthEventEntity): Boolean {
        return event.date <= LocalDate.now()
    }

    fun clearErrorMessage() {
        uiState = uiState.copy(
            errorMessage = null
        )
    }
}