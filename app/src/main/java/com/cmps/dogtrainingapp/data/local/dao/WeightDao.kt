package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {
    @Insert
    suspend fun insert(entry: WeightEntryEntity): Long

    @Query("SELECT * FROM weight_entries WHERE petId = :pid ORDER BY date ASC")
    fun getAllForPet(pid: Long): Flow<List<WeightEntryEntity>>

    @Query("""SELECT * FROM weight_entries WHERE petId = :pid ORDER BY id DESC LIMIT 1 """)
    fun getLatestForPet(pid: Long): Flow<WeightEntryEntity?>
}