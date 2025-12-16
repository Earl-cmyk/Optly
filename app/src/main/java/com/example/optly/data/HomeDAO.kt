package com.example.optly.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(home: Home)

    @Update
    suspend fun update(home: Home)

    @Delete
    suspend fun delete(home: Home)

    @Query("SELECT * FROM home ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Home>>

    @Query("SELECT * FROM home WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): Home?
}
