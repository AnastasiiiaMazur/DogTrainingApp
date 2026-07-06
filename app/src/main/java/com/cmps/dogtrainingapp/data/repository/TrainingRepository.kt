package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.data.model.CourseLevel
import com.cmps.dogtrainingapp.data.source.json.TrainingJsonSource

class TrainingRepository(
    private val trainingJsonSource: TrainingJsonSource
) {
//    fun getCourses(): List<Course> {
//        return trainingJsonSource.getCourses()
//    }

    fun getCourses(): List<Course> {
        return trainingJsonSource
            .getCourses()
            .sortedBy {
                when (it.level) {
                    CourseLevel.BEGINNER -> 0
                    CourseLevel.INTERMEDIATE -> 1
                    CourseLevel.ADVANCED -> 2
                }
            }
    }
}