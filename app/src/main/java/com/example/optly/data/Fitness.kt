package com.example.optly.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "fitness_activities")
data class Fitness(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val activityType: String,
    val duration: Int, // in minutes
    val caloriesBurned: Int,
    val date: Date,
    val notes: String? = null,
    val distance: Float? = null, // in kilometers
    val isCompleted: Boolean = true
)