package com.cmps.dogtrainingapp.ui.screens.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class DashboardViewModelFactory(
    //private val healthRepo: HealthEventRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}