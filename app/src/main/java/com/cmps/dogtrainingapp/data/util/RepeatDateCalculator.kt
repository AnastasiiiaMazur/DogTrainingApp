package com.cmps.dogtrainingapp.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import java.time.LocalDate


@RequiresApi(Build.VERSION_CODES.O)
object RepeatDateCalculator {

    /**
     * Calculates one occurrence after the supplied date.
     */
    fun nextDate(
        currentDate: LocalDate,
        repeatInterval: RepeatInterval
    ): LocalDate {
        return when (repeatInterval) {
            RepeatInterval.NEVER -> currentDate
            RepeatInterval.DAILY -> currentDate.plusDays(1)
            RepeatInterval.WEEKLY -> currentDate.plusWeeks(1)
            RepeatInterval.MONTHLY -> currentDate.plusMonths(1)
            RepeatInterval.YEARLY -> currentDate.plusYears(1)
        }
    }

    /**
     * Moves an overdue event forward until its date is today
     * or sometime in the future.
     *
     * Example:
     *
     * Event date: 25 July
     * Today: 28 July
     * Repeat: DAILY
     *
     * Result: 28 July
     */
    fun currentOrNextDate(
        eventDate: LocalDate,
        today: LocalDate,
        repeatInterval: RepeatInterval
    ): LocalDate {
        if (repeatInterval == RepeatInterval.NEVER) {
            return eventDate
        }

        var nextDate = eventDate

        while (nextDate < today) {
            nextDate = nextDate(
                currentDate = nextDate,
                repeatInterval = repeatInterval
            )
        }

        return nextDate
    }
}