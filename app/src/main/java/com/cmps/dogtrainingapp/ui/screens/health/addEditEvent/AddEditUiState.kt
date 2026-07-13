package com.cmps.dogtrainingapp.ui.screens.health.addEditEvent

import android.os.Build
import androidx.annotation.RequiresApi
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.HealthEventType
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
data class AddEditUiState (
    val healthEvent: HealthEventEntity? = null,
    val editingEventId: Long? = null,

    val selectedTitle: String = "",
    val selectedNotes: String = "",
    val selectedDate: LocalDate = LocalDate.now(),
    val selectedTime: LocalTime = LocalTime.now(),
    val selectedType: HealthEventType = HealthEventType.OTHER,
    val selectedInterval: RepeatInterval = RepeatInterval.NEVER,

    val errorMessage: String? = null
)