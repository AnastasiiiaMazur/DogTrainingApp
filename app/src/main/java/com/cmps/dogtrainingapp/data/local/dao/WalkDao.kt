package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cmps.dogtrainingapp.data.local.entity.WalkEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WalkDao {
    @Query("SELECT * FROM walk_events WHERE petId = :petId ORDER BY date DESC")
    fun getWalksForPet(petId: Long): Flow<List<WalkEventEntity>>

    @Update
    suspend fun updateWalk(walk: WalkEventEntity)

    @Query("DELETE FROM walk_events WHERE id = :eventId")
    suspend fun deleteByIdWalk(eventId: Long)

    @Insert
    suspend fun insertWalk(walk: WalkEventEntity)
}