package com.cmps.dogtrainingapp.ui.screens.dashboard.recommendation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository


@Suppress("UNCHECKED_CAST")
class RecViewModelFactory(
    private val recsRepo: DailyRecommendationsRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecViewModel::class.java)) {
            return RecViewModel(recsRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}