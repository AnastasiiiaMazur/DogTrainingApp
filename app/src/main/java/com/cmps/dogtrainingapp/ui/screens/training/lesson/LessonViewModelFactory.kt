package com.cmps.dogtrainingapp.ui.screens.training.lesson

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.TrainingRepository


@Suppress("UNCHECKED_CAST")
class LessonViewModelFactory(
    private val trainingRepo: TrainingRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LessonViewModel::class.java)) {
            return LessonViewModel(trainingRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}