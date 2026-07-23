package com.cmps.dogtrainingapp.ui.screens.training

import com.cmps.dogtrainingapp.data.model.course.Course

data class TrainingUiState(
    val courses: List<Course> = emptyList(),
    val completedLessonsByCourse: Map<String, Int> = emptyMap()
)