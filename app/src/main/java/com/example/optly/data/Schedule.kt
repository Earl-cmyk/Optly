package com.example.optly.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedules")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String? = null,
    val startTime: Date,
    val endTime: Date,
    val location: String? = null,
    val isAllDay: Boolean = false,
    val isRecurring: Boolean = false,
    val recurrenceRule: String? = null, // e.g., "DAILY", "WEEKLY", "MONTHLY"
    val category: String? = null,
    val isCompleted: Boolean = false,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date()
) {
    val isPast: Boolean
        get() = endTime.before(Date())

    val isOngoing: Boolean
        get() = !isPast && !startTime.after(Date())
}