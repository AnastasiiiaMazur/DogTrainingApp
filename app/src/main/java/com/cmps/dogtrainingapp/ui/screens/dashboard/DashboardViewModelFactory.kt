package com.cmps.dogtrainingapp.ui.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import com.cmps.dogtrainingapp.data.repository.HealthEventRepository
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.TrainingRepository


@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(
    private val recsRepository: DailyRecommendationsRepository,
    private val petRepo: PetRepository,
    private val trainingRepo: TrainingRepository,
    private val healthRepo: HealthEventRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(recsRepository, petRepo, trainingRepo, healthRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}