package com.vasiliev.onelook.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OneLookDao {

    @Query("SELECT * FROM tasks ORDER BY createdAt ASC")
    fun observeTasks(): Flow<List<TaskEntity>>

    @Query("SELECT COUNT(*) FROM tasks")
    suspend fun taskCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Query("UPDATE tasks SET completed = :completed WHERE id = :taskId")
    suspend fun setTaskCompleted(taskId: Long, completed: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivitySession(session: ActivitySessionEntity): Long

    @Query("SELECT * FROM activity_sessions ORDER BY finishedAt DESC")
    fun observeActivitySessions(): Flow<List<ActivitySessionEntity>>
}
