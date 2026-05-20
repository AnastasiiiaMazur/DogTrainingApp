package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.WeightDao
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

class WeightRepository (
    private val weightDao: WeightDao,
    private val petPrefs: SelectedPetPreferences
) {

    fun getAllWeights(): Flow<List<WeightEntryEntity>> {

        return petPrefs.selectedPetIdFlow
            .flatMapLatest { petId ->
                weightDao.getAllForPet(petId)
            }
    }

    fun getLatestWeight(): Flow<WeightEntryEntity?> {

        return petPrefs.selectedPetIdFlow
            .flatMapLatest { petId ->
                weightDao.getLatestForPet(petId)
            }
    }

    suspend fun addNewWeightEntry(weight: WeightEntryEntity) {
        weightDao.insert(weight)
    }
}