package com.cmps.dogtrainingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pets")
    fun getAll(): Flow<List<PetEntity>>

    @Insert
    suspend fun insert(pet: PetEntity): Long

    @Update
    suspend fun update(pet: PetEntity)

    @Delete
    suspend fun delete(pet: PetEntity)

    @Query("SELECT * FROM pets LIMIT 1")
    fun getFirstPetFlow(): Flow<PetEntity?>

    @Query("SELECT * FROM pets WHERE id = :id")
    fun getByIdFlow(id: Long): Flow<PetEntity?>
}