package com.cmps.dogtrainingapp.domain.model

data class DailyRecommendation(
    val id: String,
    val title: String,
    val imageName: String,
    val goal: String,
    val tips: List<String>,
    val dailyAction: String
)