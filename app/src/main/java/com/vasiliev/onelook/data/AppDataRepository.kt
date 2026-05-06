package com.vasiliev.onelook.data

import android.content.Context
import com.vasiliev.onelook.data.local.ActivitySessionEntity
import com.vasiliev.onelook.data.local.OneLookDatabase
import com.vasiliev.onelook.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.Locale

class AppDataRepository(context: Context) {

    private val dao = OneLookDatabase.getInstance(context).oneLookDao()

    fun observeTasks(): Flow<List<TaskEntity>> = dao.observeTasks()

    fun observeActivitySessions(): Flow<List<ActivitySessionEntity>> = dao.observeActivitySessions()

    suspend fun seedDefaultTasks() {
        if (dao.taskCount() > 0) return

        dao.insertTasks(
            listOf(
                TaskEntity(
                    title = "Breath training",
                    subtitle = "Continue exercise",
                    category = "activity",
                    completed = true
                ),
                TaskEntity(
                    title = "Omega 3",
                    subtitle = "1 pill after meal",
                    category = "supplement",
                    completed = true
                ),
                TaskEntity(
                    title = "Vitamin D",
                    subtitle = "1 sachet before meal",
                    category = "supplement"
                ),
                TaskEntity(
                    title = "Step Goal",
                    subtitle = "4 456 / 10 000",
                    category = "activity",
                    progressCurrent = 4456,
                    progressTarget = 10000
                )
            )
        )
    }

    suspend fun addActivityTask(type: String = "Walking", timeOfDay: String = "Morning") {
        dao.insertTask(
            TaskEntity(
                title = type,
                subtitle = "$timeOfDay activity",
                category = "activity"
            )
        )
    }

    suspend fun addSupplementTask(name: String = "New supplement", form: String = "Pill") {
        dao.insertTask(
            TaskEntity(
                title = name,
                subtitle = "1 $form after meal",
                category = "supplement"
            )
        )
    }

    suspend fun setTaskCompleted(taskId: Long, completed: Boolean) {
        dao.setTaskCompleted(taskId, completed)
    }

    suspend fun saveWalkingSession(durationSeconds: Int, steps: Int, calories: Double) {
        dao.insertActivitySession(
            ActivitySessionEntity(
                type = "Walking",
                durationSeconds = durationSeconds,
                steps = steps,
                calories = calories
            )
        )

        dao.insertTask(
            TaskEntity(
                title = "Walking",
                subtitle = "${formatDuration(durationSeconds)} - $steps steps - ${formatCalories(calories)} kcal",
                category = "activity",
                completed = true,
                progressCurrent = steps,
                progressTarget = 10000
            )
        )
    }

    private fun formatDuration(totalSeconds: Int): String {
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return "%02d:%02d".format(minutes, seconds)
    }

    private fun formatCalories(calories: Double): String {
        return String.format(Locale.US, "%.1f", calories)
    }
}
