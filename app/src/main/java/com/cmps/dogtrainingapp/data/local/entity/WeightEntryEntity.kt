package com.cmps.dogtrainingapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "weight_entries",
    foreignKeys = [
        ForeignKey(
            entity        = PetEntity::class,
            parentColumns = ["id"],
            childColumns  = ["petId"],
            onDelete      = ForeignKey.CASCADE
        )
    ],
    indices = [ Index("petId") ]
)

data class WeightEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val petId: Long,
    val date: LocalDate,
    val weightKg: Float
)