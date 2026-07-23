package com.cmps.dogtrainingapp.ui.screens.training.course

import com.cmps.dogtrainingapp.data.model.course.Course


data class CourseUiState(
    val course: Course? = null,
    val completedLessonIds: Set<String> = emptySet(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)