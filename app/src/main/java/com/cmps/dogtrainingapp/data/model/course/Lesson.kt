package com.cmps.dogtrainingapp.data.model.course

data class Lesson(
    val id: String,
    val title: String,
    val image: String,
    val goal: String,
    val duration: String,
    val steps: List<String>,
    val tips: List<String>,
    val completionCriteria: String
)