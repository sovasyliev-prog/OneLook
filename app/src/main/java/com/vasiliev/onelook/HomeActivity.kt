package com.vasiliev.onelook

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.vasiliev.onelook.notifications.EXTRA_OPEN_WALKING
import com.vasiliev.onelook.notifications.WalkingReminderScheduler
import com.vasiliev.onelook.ui.screens.home.HomeRoot
import com.vasiliev.onelook.ui.theme.AppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestNotificationPermissionIfNeeded()
        WalkingReminderScheduler.scheduleDailyReminder(this)

        setContent {
            AppTheme {
                HomeRoot(
                    openWalkingOnStart = intent.getBooleanExtra(EXTRA_OPEN_WALKING, false)
                )
            }
        }
    }

    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) return

        val granted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED

        if (!granted) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        }
    }
}

private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 7001
