package com.cmps.dogtrainingapp.ui.screens.health.addEditEvent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

@RequiresApi(Build.VERSION_CODES.O)
class AddEditViewModel(): ViewModel() {

    var state by mutableStateOf(AddEditUiState())
        private set
}