package com.vasiliev.onelook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vasiliev.onelook.ui.screens.SplashScreen
import com.vasiliev.onelook.ui.theme.AppTheme

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                SplashScreen(
                    onStartClick = {
                        finish() // Splash закривається, бо інших екранів ще немає
                    }
                )
            }
        }
    }
}
