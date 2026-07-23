package com.cmps.dogtrainingapp.ui.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository

class DashboardViewModel(
    private val recsRepository: DailyRecommendationsRepository
): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set

    init {
        loadRecs()
    }

    private fun loadRecs() {
        val recs = recsRepository.getRecommendations()

        uiState = uiState.copy(
            dailyRecs = recs
        )
    }
}