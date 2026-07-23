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
class HealthEventViewModel(
    private val healthRepo: HealthEventRepository
): ViewModel() {

    var uiState by mutableStateOf(HealthEventUiState())
        private set

    init {
        loadEvents()
    }

    private fun loadEvents() {
        viewModelScope.launch {
            healthRepo.getEventsForPet().collect { events ->

                val now = LocalDateTime.now()

                val today = LocalDate.now()
                val startOfWeek = today.with(DayOfWeek.MONDAY)
                val endOfWeek = startOfWeek.plusDays(6)

                val overdue = events.count {
                    !it.isCompleted && LocalDateTime.of(it.date, it.time).isBefore(now)
                }

                val upcoming = events.count {
                    !it.isCompleted && LocalDateTime.of(it.date, it.time).isAfter(now)
                }

                val weeklyEvents = events.filter {
                    it.date in startOfWeek..endOfWeek
                }

                val completed = events.count { it.isCompleted }

                uiState = uiState.copy(
                    healthEvents = events,
                    weeklyEvents = weeklyEvents,

                    totalEvents = events.size,
                    upcomingEvents = upcoming,
                    overdueEvents = overdue,
                    completedEvents = completed
                )
            }
        }
    }

    fun onCompleteClicked(event: HealthEventEntity) {
        viewModelScope.launch {

            val isNowCompleted = !event.isCompleted

            val updatedEvent = event.copy(
                isCompleted = isNowCompleted,
                completedOn = if (isNowCompleted) {
                    LocalDateTime.now()
                } else {
                    null
                }
            )

            healthRepo.updateEvent(updatedEvent)
        }
    }
}