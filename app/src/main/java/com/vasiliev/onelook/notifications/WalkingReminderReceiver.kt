package com.vasiliev.onelook.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.vasiliev.onelook.HomeActivity
import com.vasiliev.onelook.R

class WalkingReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        createChannel(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val granted = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!granted) return
        }

        val openWalkingIntent = Intent(context, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_OPEN_WALKING, true)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            WALKING_NOTIFICATION_REQUEST_CODE,
            openWalkingIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, WALKING_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_walk)
            .setContentTitle("Let's go on the walk")
            .setContentText("You have not walked today yet. Time to stretch your legs a bit.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("You have not walked today yet. Time to stretch your legs a bit.")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(R.drawable.ic_walk, "Start now", pendingIntent)
            .build()

        NotificationManagerCompat.from(context).notify(WALKING_NOTIFICATION_ID, notification)
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return

        val channel = NotificationChannel(
            WALKING_CHANNEL_ID,
            "Walking reminders",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Daily reminders to start walking activity"
        }

        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }
}

private const val WALKING_CHANNEL_ID = "walking_reminders"
private const val WALKING_NOTIFICATION_ID = 6002
private const val WALKING_NOTIFICATION_REQUEST_CODE = 6003
