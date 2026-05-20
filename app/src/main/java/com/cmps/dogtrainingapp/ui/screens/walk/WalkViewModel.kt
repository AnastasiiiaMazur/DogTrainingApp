package com.cmps.dogtrainingapp.ui.screens.walk

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import com.cmps.dogtrainingapp.data.repository.WalkHubRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
class WalkViewModel(
    private val walkRepo: WalkHubRepository
): ViewModel() {

    var uiState by mutableStateOf(WalkUiState())
        private set

    init {
        loadWalks()
    }

    private fun loadWalks() {
        viewModelScope.launch {
            walkRepo.getAllWalks().collect { walks ->

                val totalTime = walks.sumOf { it.durationMinutes }

                uiState = uiState.copy(
                    walks = walks,

                    totalWalks = walks.size,
                    totalWalkTime = totalTime,
                    averageDuration = if (walks.isNotEmpty()) totalTime.toFloat() / walks.size else 0f
                )
            }
        }
    }

    fun onDurationChanged(newDuration: String) {
        uiState = uiState.copy(
            durationText = newDuration
        )
    }

    fun onNotesChanged(newNote: String) {
        uiState = uiState.copy(
            notes = newNote
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

    fun onSaveClicked() {

        uiState = uiState.copy(
            errorMessage = null
        )

        if (uiState.durationText.isBlank()) {
            uiState = uiState.copy(
                errorMessage = "* Duration field cannot be empty!"
            )
            return
        }

        val duration = uiState.durationText.toIntOrNull()

        if (duration == null || duration <= 0) {
            uiState = uiState.copy(
                errorMessage = "* Duration must be a valid number!"
            )
            return
        }

        viewModelScope.launch {

            val walk = WalkEventEntity(
                date = uiState.selectedDate,
                time = uiState.selectedTime,
                durationMinutes = duration,
                notes = uiState.notes,
                petId = walkRepo.getSelectedPetId()
            )

            walkRepo.addWalk(walk)

            uiState = uiState.copy(
                durationText = "",
                notes = "",
                errorMessage = null
            )
        }
    }

    fun onEditClicked() {
        uiState = uiState.copy(
        )
    }
}