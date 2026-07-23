package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.model.recs.Recommendation
import com.cmps.dogtrainingapp.data.source.json.RecsJsonSource

class DailyRecommendationsRepository(
    private val recsJsonSource: RecsJsonSource
) {

    fun getRecommendations(): List<Recommendation> {
        return recsJsonSource
            .getRecs()

    }
}