package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.HealthEventDao
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class HealthEventRepository(
    private val healthDao: HealthEventDao,
    private val petPrefs: SelectedPetPreferences
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
}