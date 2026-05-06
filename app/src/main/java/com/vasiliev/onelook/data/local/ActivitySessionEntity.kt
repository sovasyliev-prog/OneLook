package com.vasiliev.onelook.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activity_sessions")
data class ActivitySessionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val type: String,
    val durationSeconds: Int,
    val steps: Int,
    val calories: Double,
    val finishedAt: Long = System.currentTimeMillis()
)
