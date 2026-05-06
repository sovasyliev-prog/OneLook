package com.vasiliev.onelook.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class, ActivitySessionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OneLookDatabase : RoomDatabase() {

    abstract fun oneLookDao(): OneLookDao

    companion object {
        @Volatile
        private var instance: OneLookDatabase? = null

        fun getInstance(context: Context): OneLookDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    OneLookDatabase::class.java,
                    "onelook.db"
                ).build().also { instance = it }
            }
        }
    }
}
