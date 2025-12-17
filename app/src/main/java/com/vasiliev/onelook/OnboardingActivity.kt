package com.vasiliev.onelook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vasiliev.onelook.ui.screens.OnboardingScreen
import com.vasiliev.onelook.ui.theme.AppTheme

private const val PREFS_NAME = "app_prefs"
private const val KEY_ONBOARDING_SEEN = "onboarding_seen"

class OnboardingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                OnboardingScreen(
                    onFinish = {
                        getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                            .edit()
                            .putBoolean(KEY_ONBOARDING_SEEN, true)
                            .apply()

                        startActivity(Intent(this, AuthActivity::class.java))
                        finish()
                    }
                )
            }
        }
    }
}
