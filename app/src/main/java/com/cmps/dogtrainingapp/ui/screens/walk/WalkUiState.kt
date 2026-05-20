package com.cmps.dogtrainingapp.ui.screens.walk

import android.os.Build
import androidx.annotation.RequiresApi
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
data class WalkUiState(
    val walks: List<WalkEventEntity> = emptyList(),

    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTime: LocalTime = LocalTime.now(),

    val durationText: String = "",
    val notes: String = "",

    val totalWalks: Int = 0,
    val averageDuration: Float = 0f,
    val totalWalkTime: Int = 0,

    val isEditing: Boolean = false,
    val errorMessage: String? = null
)