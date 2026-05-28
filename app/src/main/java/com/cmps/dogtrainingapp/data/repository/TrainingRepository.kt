package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.data.source.json.TrainingJsonSource

class TrainingRepository(
    private val trainingJsonSource: TrainingJsonSource
) {
    fun getCourses(): List<Course> {
        return trainingJsonSource.getCourses()
    }
}