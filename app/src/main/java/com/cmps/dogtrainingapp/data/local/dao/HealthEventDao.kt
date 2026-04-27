package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cmps.dogtrainingapp.data.local.entity.HealthEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthEventDao {
    @Query("""SELECT * FROM health_events 
                WHERE petId = :petId 
                ORDER BY date, time""")
    fun getEventsForPet(petId: Long): Flow<List<HealthEventEntity>>

    @Insert
    suspend fun insert(event: HealthEventEntity): Long

    @Update
    suspend fun update(event: HealthEventEntity)

    @Query("DELETE FROM health_events WHERE id = :eventId")
    suspend fun deleteById(eventId: Long)

    @Query("SELECT * FROM health_events WHERE id = :id")
    suspend fun getById(id: Long): HealthEventEntity?
}