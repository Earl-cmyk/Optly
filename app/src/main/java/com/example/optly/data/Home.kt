package com.example.optly.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home")
data class Home(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,          // e.g. task name
    val description: String?,   // optional details
    val isImportant: Boolean,   // Eisenhower matrix tag
    val isUrgent: Boolean,      // Eisenhower matrix tag
    val createdAt: Long = System.currentTimeMillis()
)
