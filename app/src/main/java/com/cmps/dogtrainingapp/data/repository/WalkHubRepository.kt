package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.WalkDao
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow

class WalkHubRepository(
    private val walkDao: WalkDao,
    private val petPrefs: SelectedPetPreferences
) {
    fun getAllWalks(): Flow<List<WalkEventEntity>> {
        return walkDao.getWalksForPet(petPrefs.getSelectedPetId())
    }

    suspend fun addWalk(walk: WalkEventEntity): Long {
        return walkDao.insertWalk(walk)
    }

    suspend fun deleteWalk(id: Long) {
        walkDao.deleteByIdWalk(id)
    }

    fun getSelectedPetId(): Long {
        return petPrefs.getSelectedPetId()
    }
}