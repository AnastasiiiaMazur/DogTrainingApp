package com.cmps.dogtrainingapp.ui.screens.dashboard

import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.model.course.Course
import com.cmps.dogtrainingapp.data.model.recs.Recommendation

data class DashboardUiState (
    val currentPet: PetEntity? = null,
    val upcomingActivities: List<HealthEventEntity> = emptyList(),
    val courseProgress: Course? = null,
    val dailyRecs: List<Recommendation> = emptyList()
)