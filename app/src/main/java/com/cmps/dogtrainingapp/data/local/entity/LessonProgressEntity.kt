package com.cmps.dogtrainingapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "lesson_progress",
    foreignKeys = [
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["id"],
            childColumns = ["petId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(
            value = ["petId", "courseId", "lessonId"],
            unique = true
        )
    ]
)
data class LessonProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val petId: Long,
    val courseId: String,
    val lessonId: String,
    val completed: Boolean = false
)