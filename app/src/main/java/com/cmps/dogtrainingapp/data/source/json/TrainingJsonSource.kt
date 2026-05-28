package com.cmps.dogtrainingapp.data.source.json

import android.content.Context
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.data.model.CoursesResponse
import com.google.gson.Gson
import java.io.InputStreamReader

class TrainingJsonSource(
    private val context: Context
) {

    fun getCourses(): List<Course> {
        val inputStream = context.resources.openRawResource(R.raw.courses)
        val reader = InputStreamReader(inputStream)
        val response = Gson().fromJson(reader, CoursesResponse::class.java)

        return response.courses
    }
}