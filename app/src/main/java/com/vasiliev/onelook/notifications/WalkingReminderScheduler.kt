package com.vasiliev.onelook.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

const val EXTRA_OPEN_WALKING = "com.vasiliev.onelook.extra.OPEN_WALKING"

object WalkingReminderScheduler {

    fun scheduleDailyReminder(context: Context) {
        val alarmManager = context.getSystemService(AlarmManager::class.java)
        val intent = Intent(context, WalkingReminderReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            WALKING_REMINDER_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            nextTenAM(),
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun nextTenAM(): Long {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 10)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }.timeInMillis
    }
}

private const val WALKING_REMINDER_REQUEST_CODE = 6001
