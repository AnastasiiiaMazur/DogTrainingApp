package com.cmps.dogtrainingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Gender(val displayName: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other")
}

@Entity(tableName = "pets")
data class PetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val name: String,
    val breed: String,
    val dateOfBirth: String?,
    val gender: Gender,
    val imageUri: String?
)