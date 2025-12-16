package com.example.optly.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val amount: Double,
    val category: String,
    val startDate: Date,
    val endDate: Date,
    val currentSpent: Double = 0.0,
    val isActive: Boolean = true
)