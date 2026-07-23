package com.cmps.dogtrainingapp.data.model.course

import com.cmps.dogtrainingapp.data.model.course.CourseLevel
import com.cmps.dogtrainingapp.data.model.course.Lesson

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val level: CourseLevel,
    val imageName: String,
    val lessons: List<Lesson>
)