package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.WeightDao
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow

class WeightRepository (
    private val weightDao: WeightDao,
    private val petPrefs: SelectedPetPreferences
) {

    fun getAllWeights(): Flow<List<WeightEntryEntity>> {
        return weightDao.getAllForPet(petPrefs.getSelectedPetId())
    }

    fun getLatestWeight(): Flow<WeightEntryEntity?> {
        return weightDao.getLatestForPet(petPrefs.getSelectedPetId())
    }

    suspend fun addNewWeightEntry(weight: WeightEntryEntity) {
        weightDao.insert(weight)
    }
}