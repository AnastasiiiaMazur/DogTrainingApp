package com.cmps.dogtrainingapp.data.util

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.edit
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class HealthEventPreferences(
    context: Context
) {

    private val preferences =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    fun getLastRefreshDate(): LocalDate? {
        return preferences
            .getString(KEY_LAST_REFRESH_DATE, null)
            ?.let { storedDate ->
                runCatching {
                    LocalDate.parse(storedDate)
                }.getOrNull()
            }
    }

    fun saveLastRefreshDate(date: LocalDate) {
        preferences.edit {
            putString(
                KEY_LAST_REFRESH_DATE,
                date.toString()
            )
        }
    }

    companion object {
        private const val PREF_NAME =
            "health_event_preferences"

        private const val KEY_LAST_REFRESH_DATE =
            "last_repeat_refresh_date"
    }
}