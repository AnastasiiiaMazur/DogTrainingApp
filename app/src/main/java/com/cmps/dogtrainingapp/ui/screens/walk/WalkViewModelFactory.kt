package com.cmps.dogtrainingapp.ui.screens.walk

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.WalkHubRepository


@Suppress("UNCHECKED_CAST")
class WalkViewModelFactory(
    private val walkRepo: WalkHubRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalkViewModel::class.java)) {
            return WalkViewModel(walkRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}