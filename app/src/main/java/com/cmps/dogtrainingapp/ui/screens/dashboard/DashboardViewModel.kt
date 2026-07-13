package com.cmps.dogtrainingapp.ui.screens.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class DashboardViewModel(): ViewModel() {

    var uiState by mutableStateOf(DashboardUiState())
        private set
}