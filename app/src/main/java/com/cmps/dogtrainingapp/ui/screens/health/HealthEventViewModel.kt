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

                val overdue = events.count {
                    !it.isCompleted &&
                            LocalDateTime.of(it.date, it.time).isBefore(now)
                }

                val upcoming = events.count {
                    !it.isCompleted &&
                            LocalDateTime.of(it.date, it.time).isAfter(now)
                }

                uiState = uiState.copy(
                    healthEvents = events,

                    totalEvents = events.size,
                    upcomingEvents = upcoming,
                    overdueEvents = overdue
                )
            }
        }
    }

    fun onSaveClicked() {
        uiState = uiState.copy(
            errorMessage = null
        )

        if (uiState.selectedTitle.isBlank()) {
            uiState = uiState.copy(
                errorMessage = "* Title cannot be empty!"
            )
            return
        }

        val editingId = uiState.editingEventId

        viewModelScope.launch {
            val event = HealthEventEntity(
                id = editingId ?: 0L,
                title = uiState.selectedTitle,
                notes = uiState.selectedNotes,
                type = uiState.selectedType,
                date = uiState.selectedDate,
                time = uiState.selectedTime,
                repeat = uiState.selectedInterval,
                petId = healthRepo.getSelectedPetId()
            )

            if (editingId == null) {
                healthRepo.addNewEvent(event)
            } else {
                healthRepo.updateEvent(event)
            }

            uiState = uiState.copy(
                errorMessage = null,
                editingEventId = null,
                selectedTitle = "",
                selectedNotes = "",
                selectedDate = LocalDate.now(),
                selectedTime = LocalTime.now(),
                selectedType = HealthEventType.OTHER,
                selectedInterval = RepeatInterval.NEVER
            )
        }
    }

    fun onDeleteClicked(id: Long) {
        viewModelScope.launch {
            healthRepo.deleteEvent(id)
        }
    }

    fun onEditClicked(event: HealthEventEntity) {
        uiState = uiState.copy(
            editingEventId = event.id,
            selectedDate = event.date,
            selectedTime = event.time,
            selectedTitle = event.title,
            selectedNotes = event.notes ?: "",
            selectedType = event.type,
            selectedInterval = event.repeat
        )
    }

    fun onTitleChanged(newTitle: String) {
        uiState = uiState.copy(
            selectedTitle = newTitle
        )
    }

    fun onNoteChanged(newNote: String) {
        uiState = uiState.copy(
            selectedNotes = newNote
        )
    }

    fun onDateChanged(date: LocalDate) {
        uiState = uiState.copy(
            selectedDate = date
        )
    }

    fun onTimeChanged(time: LocalTime) {
        uiState = uiState.copy(
            selectedTime = time
        )
    }

    fun onTypeChanged(newType: HealthEventType) {
        uiState = uiState.copy(
            selectedType = newType
        )
    }

    fun onRepeatChanged(newRepeat: RepeatInterval) {
        uiState = uiState.copy(
            selectedInterval = newRepeat
        )
    }

    fun onCompleteClicked(event: HealthEventEntity) {
        event.copy(
            isCompleted = true,
            completedOn = LocalDateTime.now()
        )
    }
}