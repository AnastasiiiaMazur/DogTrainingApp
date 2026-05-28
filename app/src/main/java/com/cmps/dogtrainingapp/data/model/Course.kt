package com.cmps.dogtrainingapp.data.model

data class Course(
    val id: String,
    val title: String,
    val description: String,
    val level: String,
    val imageName: String,
    val lessons: List<Lesson>
)