package com.cmps.dogtrainingapp.data.model.recs

data class Recommendation (
    val id: String,
    val title: String,
    val imageName: String,
    val goal: String,
    val tips: List<String>,
    val dailyAction: String
)