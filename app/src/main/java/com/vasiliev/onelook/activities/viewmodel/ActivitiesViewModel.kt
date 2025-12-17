package com.vasiliev.onelook.activities.viewmodel

import androidx.lifecycle.ViewModel
import com.vasiliev.onelook.activities.data.ActivityRepository
import com.vasiliev.onelook.activities.data.ActivityType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ActivitiesViewModel : ViewModel() {

    private val repo = ActivityRepository()

    private val _activities = MutableStateFlow<List<ActivityType>>(emptyList())
    val activities: StateFlow<List<ActivityType>> = _activities

    private val _selectedType = MutableStateFlow<ActivityType?>(null)
    val selectedType: StateFlow<ActivityType?> = _selectedType

    val availableTypes = repo.getAvailableTypes()

    fun selectType(type: ActivityType) {
        _selectedType.value = type
    }

    fun startActivity() {
        _selectedType.value?.let {
            repo.addActivity(it)
            _activities.value = repo.getActivities()
        }
    }
}
