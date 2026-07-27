package com.cmps.dogtrainingapp.ui.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import com.cmps.dogtrainingapp.data.repository.PetRepository
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val recsRepository: DailyRecommendationsRepository,
    private val petRepo: PetRepository
): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    init {
        loadRecs()
        loadCurrentPet()
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
}