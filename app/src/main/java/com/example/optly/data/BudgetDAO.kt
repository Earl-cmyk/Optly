package com.example.optly.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface BudgetDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(budget: Budget): Long

    @Update
    suspend fun update(budget: Budget)

    @Delete
    suspend fun delete(budget: Budget)

    @Query("SELECT * FROM budgets WHERE id = :budgetId")
    suspend fun getBudgetById(budgetId: Long): Budget?

    @Query("SELECT * FROM budgets WHERE isActive = 1 ORDER BY endDate ASC")
    fun getAllActiveBudgets(): LiveData<List<Budget>>

    @Query("SELECT * FROM budgets WHERE category = :category AND isActive = 1")
    fun getBudgetsByCategory(category: String): LiveData<List<Budget>>

    @Query("SELECT * FROM budgets WHERE endDate < :currentDate AND isActive = 1")
    suspend fun getExpiredBudgets(currentDate: Date): List<Budget>

    @Query("UPDATE budgets SET currentSpent = currentSpent + :amount WHERE id = :budgetId")
    suspend fun updateSpentAmount(budgetId: Long, amount: Double)

    @Query("SELECT SUM(amount) FROM budgets WHERE isActive = 1")
    suspend fun getTotalBudgetAmount(): Double

    @Query("SELECT SUM(currentSpent) FROM budgets WHERE isActive = 1")
    suspend fun getTotalSpentAmount(): Double

    @Query("SELECT * FROM budgets WHERE name LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchBudgets(query: String): LiveData<List<Budget>>
}
