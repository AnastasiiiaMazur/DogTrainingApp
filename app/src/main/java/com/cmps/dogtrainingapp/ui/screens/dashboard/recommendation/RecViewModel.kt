package com.cmps.dogtrainingapp.ui.screens.dashboard.recommendation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import kotlinx.coroutines.launch

class RecViewModel(
    private val recsRepository: DailyRecommendationsRepository
): ViewModel() {

    var uiState by mutableStateOf(RecUiState())
        private set

    fun loadRec(recId: String) {

        viewModelScope.launch {
            val rec = recsRepository.getRecommendation(recId)

            uiState = uiState.copy(
                rec = rec
            )
        }
    }
}