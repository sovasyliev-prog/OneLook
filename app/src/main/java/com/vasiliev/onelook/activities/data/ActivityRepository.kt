package com.vasiliev.onelook.activities.data

import com.vasiliev.onelook.R

class ActivityRepository {

    private val activities = mutableListOf<ActivityType>()

    fun getAvailableTypes(): List<ActivityType> = listOf(
        ActivityType(1, "Running", R.drawable.ic_run),
        ActivityType(2, "Walking", R.drawable.ic_walk),
        ActivityType(3, "Fitness", R.drawable.ic_fitness),
        ActivityType(4, "Yoga", R.drawable.ic_yoga),
        ActivityType(5, "Rollers", R.drawable.ic_rollers),
        ActivityType(6, "Breathing", R.drawable.ic_breath)
    )

    fun addActivity(type: ActivityType) {
        activities.add(type)
    }

    fun getActivities(): List<ActivityType> = activities
}
