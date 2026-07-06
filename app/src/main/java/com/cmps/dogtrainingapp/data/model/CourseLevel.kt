package com.cmps.dogtrainingapp.data.model

import com.google.gson.annotations.SerializedName

enum class CourseLevel(
    val displayName: String
) {
    @SerializedName("Beginner")
    BEGINNER("Beginner"),

    @SerializedName("Intermediate")
    INTERMEDIATE("Intermediate"),

    @SerializedName("Advanced")
    ADVANCED("Advanced")
}