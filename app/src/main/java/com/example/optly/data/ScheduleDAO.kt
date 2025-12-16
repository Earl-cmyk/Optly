package com.example.optly.data

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface ScheduleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(schedule: Schedule): Long

    @Update
    suspend fun update(schedule: Schedule)

    @Delete
    suspend fun delete(schedule: Schedule)

    @Query("SELECT * FROM schedules WHERE id = :scheduleId")
    suspend fun getScheduleById(scheduleId: Long): Schedule?

    @Query("SELECT * FROM schedules WHERE date(startTime/1000, 'unixepoch') = date(:date/1000, 'unixepoch')")
    fun getSchedulesByDate(date: Date): LiveData<List<Schedule>>

    @Query("""
        SELECT * FROM schedules 
        WHERE startTime >= :startDate 
        AND endTime <= :endDate
        ORDER BY startTime ASC
    """)
    fun getSchedulesInDateRange(startDate: Date, endDate: Date): LiveData<List<Schedule>>

    @Query("""
        SELECT * FROM schedules 
        WHERE isCompleted = 0 
        AND endTime >= :currentDate
        ORDER BY startTime ASC
    """)
    fun getUpcomingSchedules(currentDate: Date = Date()): LiveData<List<Schedule>>

    @Query("UPDATE schedules SET isCompleted = :isCompleted WHERE id = :scheduleId")
    suspend fun updateCompletionStatus(scheduleId: Long, isCompleted: Boolean)

    @Query("""
        SELECT * FROM schedules 
        WHERE title LIKE '%' || :query || '%' 
        OR description LIKE '%' || :query || '%'
        OR location LIKE '%' || :query || '%'
    """)
    fun searchSchedules(query: String): LiveData<List<Schedule>>

    @Query("""
        SELECT * FROM schedules 
        WHERE category = :category 
        AND startTime >= :startDate 
        AND endTime <= :endDate
        ORDER BY startTime ASC
    """)
    fun getSchedulesByCategory(category: String, startDate: Date, endDate: Date): LiveData<List<Schedule>>

    @Query("DELETE FROM schedules WHERE id = :scheduleId")
    suspend fun deleteById(scheduleId: Long)

    @Query("""
        SELECT * FROM schedules 
        WHERE isRecurring = 1 
        AND (recurrenceRule = 'DAILY' 
            OR (recurrenceRule = 'WEEKLY' AND strftime('%w', startTime/1000, 'unixepoch') = :dayOfWeek)
            OR (recurrenceRule = 'MONTHLY' AND strftime('%d', startTime/1000, 'unixepoch') = :dayOfMonth))
    """)
    suspend fun getRecurringSchedules(dayOfWeek: String, dayOfMonth: String): List<Schedule>
}