package com.cmps.dogtrainingapp.ui.screens.training.course

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.TrainingRepository


@Suppress("UNCHECKED_CAST")
class CourseViewModelFactory(
    private val trainingRepo: TrainingRepository
): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CourseViewModel::class.java)) {
            return CourseViewModel(trainingRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}