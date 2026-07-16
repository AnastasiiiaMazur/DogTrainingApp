package com.cmps.dogtrainingapp.ui.screens.health.addEditEvent

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.HealthEventRepository

@Suppress("UNCHECKED_CAST")
class AddEditViewModelFactory(
    private val healthRepo: HealthEventRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddEditViewModel::class.java)) {
            return AddEditViewModel(healthRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}