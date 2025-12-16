package com.vasiliev.onelook

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.ui.screens.SplashScreen
import com.vasiliev.onelook.ui.theme.AppTheme
import kotlinx.coroutines.delay

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val store = LocalAuthStore(this)

        setContent {
            AppTheme {
                SplashScreen(onStartClick = { /* кнопка є, але редірект робимо таймером */ })

                LaunchedEffect(Unit) {
                    delay(1000)

                    val next = if (store.isLoggedIn()) {
                        Intent(this@SplashActivity, HomeActivity::class.java)
                    } else {
                        Intent(this@SplashActivity, AuthActivity::class.java)
                    }

                    startActivity(next)
                    finish()
                }
            }
        }
    }
}
