package com.cmps.dogtrainingapp.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.cmps.dogtrainingapp.data.local.dao.HealthEventDao
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.local.entity.RepeatInterval
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import com.cmps.dogtrainingapp.data.util.HealthEventPreferences
import com.cmps.dogtrainingapp.data.util.RepeatDateCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import java.time.LocalDate
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class HealthEventRepository(
    private val healthDao: HealthEventDao,
    private val petPrefs: SelectedPetPreferences,
    private val healthEventPreferences: HealthEventPreferences
) {
    fun getEventsForPet(): Flow<List<HealthEventEntity>> {
        return petPrefs.selectedPetIdFlow
            .flatMapLatest { petId ->
                healthDao.getEventsForPet(petId)
            }
    }

    suspend fun addNewEvent(newEvent: HealthEventEntity): Long {
        return healthDao.insert(newEvent)
    }

    suspend fun updateEvent(event: HealthEventEntity) {
        return healthDao.update(event)
    }

    suspend fun deleteEvent(eventId: Long) {
        return healthDao.deleteById(eventId)
    }

    fun getSelectedPetId(): Long {
        return petPrefs.getSelectedPetId()
    }

    suspend fun getEventById(eventId: Long): HealthEventEntity? {
        return healthDao.getEventById(eventId)
    }

    suspend fun completeEvent(event: HealthEventEntity) {
        val updatedEvent =
            if (event.repeat == RepeatInterval.NEVER) {

                event.copy(
                    isCompleted = true,
                    completedOn = LocalDateTime.now()
                )

            } else {

                event.copy(
                    date = RepeatDateCalculator.nextDate(
                        currentDate = event.date,
                        repeatInterval = event.repeat
                    ),
                    isCompleted = false,
                    completedOn = null
                )
            }

        healthDao.update(updatedEvent)
    }

    suspend fun uncompleteEvent(event: HealthEventEntity) {
        healthDao.update(
            event.copy(
                isCompleted = false,
                completedOn = null
            )
        )
    }

    suspend fun refreshRepeatingEventsIfNeeded() {
        val today = LocalDate.now()
        val lastRefreshDate =
            healthEventPreferences.getLastRefreshDate()

        if (lastRefreshDate == today) {
            return
        }

        val petId = petPrefs.getSelectedPetId()

        if (petId == 0L) {
            return
        }

        val events =
            healthDao.getEventsForPetOnce(petId)

        events
            .filter { event ->
                event.repeat != RepeatInterval.NEVER &&
                        event.date < today
            }
            .forEach { event ->

                val refreshedDate =
                    RepeatDateCalculator.currentOrNextDate(
                        eventDate = event.date,
                        today = today,
                        repeatInterval = event.repeat
                    )

                healthDao.update(
                    event.copy(
                        date = refreshedDate,
                        isCompleted = false,
                        completedOn = null
                    )
                )
            }

        healthEventPreferences.saveLastRefreshDate(today)
    }

}