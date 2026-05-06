package com.vasiliev.onelook.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val subtitle: String,
    val category: String,
    val completed: Boolean = false,
    val progressCurrent: Int? = null,
    val progressTarget: Int? = null,
    val createdAt: Long = System.currentTimeMillis()
)
