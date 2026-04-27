package com.cmps.dogtrainingapp.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

enum class RepeatInterval(val displayName: String) {
    NEVER("Never"),
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly"),
    YEARLY("Yearly")
}

enum class HealthEventType(val displayName: String) {
    OTHER("Other"),
    VACCINATION("Vaccination"),
    MEDICATION("Medication"),
    VET_APPOINTMENT("Vet Appointment"),
    GROOMING("Grooming")
}

@Entity(
    tableName = "health_events",
    foreignKeys = [
        ForeignKey(
            entity = PetEntity::class,
            parentColumns = ["id"],
            childColumns = ["petId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [ Index("petId") ]
)
data class HealthEventEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val notes: String?,
    val type: HealthEventType,
    val date: LocalDate,
    val time: LocalTime,
    val repeat: RepeatInterval,
    val petId: Long,
    val isCompleted: Boolean = false,
    val completedOn: LocalDateTime? = null
)