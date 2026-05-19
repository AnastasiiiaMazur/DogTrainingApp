package com.cmps.dogtrainingapp.ui.screens.dashboard

import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.LessonProgressEntity
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.domain.model.DailyRecommendation

data class DashboardUiState (
    val currentPet: PetEntity,
    val upcomingActivities: List<HealthEventEntity> = emptyList(),
    val courseProgress: LessonProgressEntity,
    val dailyRecs: List<DailyRecommendation> = emptyList()
)