package com.cmps.dogtrainingapp.data.repository

import android.content.Context
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.domain.model.DailyRecommendation
import com.cmps.dogtrainingapp.domain.model.RecommendationsResponse
import com.google.gson.Gson

class DailyRecommendationsRepository(
    private val context: Context
) {

    fun getRecommendations(): List<DailyRecommendation> {

        val inputStream = context.resources
            .openRawResource(R.raw.daily_recs)

        val jsonString = inputStream
            .bufferedReader()
            .use { it.readText() }

        val gson = Gson()

        val response = gson.fromJson(
            jsonString,
            RecommendationsResponse::class.java
        )

        return response.recommendations
    }
}