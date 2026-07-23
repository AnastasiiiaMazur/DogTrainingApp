package com.cmps.dogtrainingapp.data.model.course

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val level: CourseLevel,
    val imageName: String,
    val lessons: List<Lesson>
)