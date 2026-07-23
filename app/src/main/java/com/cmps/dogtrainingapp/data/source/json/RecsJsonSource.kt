package com.cmps.dogtrainingapp.data.source.json

import android.content.Context
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.recs.Recommendation
import com.cmps.dogtrainingapp.data.model.recs.RecsResponse
import com.google.gson.Gson
import java.io.InputStreamReader

class RecsJsonSource(
    private val context: Context
) {

    fun getRecs(): List<Recommendation> {
        val inputStream = context.resources.openRawResource(R.raw.daily_recs)
        val reader = InputStreamReader(inputStream)
        val response = Gson().fromJson(reader, RecsResponse::class.java)

        return response.recommendations
    }
}