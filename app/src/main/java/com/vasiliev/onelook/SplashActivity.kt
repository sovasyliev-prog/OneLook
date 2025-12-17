package com.vasiliev.onelook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.ui.screens.SplashScreen
import com.vasiliev.onelook.ui.theme.AppTheme
import kotlinx.coroutines.delay

private const val PREFS_NAME = "app_prefs"
private const val KEY_ONBOARDING_SEEN = "onboarding_seen"

class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val store = LocalAuthStore(this)

        setContent {
            AppTheme {
                // ✅ Guard щоб не було подвійного startActivity() (і “UI зависає”/ANR)
                val navigatedState = remember { mutableStateOf(false) }

                fun goOnce(intent: Intent) {
                    if (navigatedState.value) return
                    navigatedState.value = true
                    startActivity(intent)
                    finish()
                }

                // --- UI (Splash) ---
                SplashScreen(
                    onGoToOnboarding = {
                        goOnce(Intent(this@SplashActivity, OnboardingActivity::class.java))
                    },
                    onGoToSignIn = {
                        goOnce(Intent(this@SplashActivity, AuthActivity::class.java))
                    }
                )

                // --- Auto redirect after 1 second (за вимогою ЛР) ---
                LaunchedEffect(Unit) {
                    delay(1000)
                    if (navigatedState.value) return@LaunchedEffect

                    val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                    val onboardingSeen = prefs.getBoolean(KEY_ONBOARDING_SEEN, false)

                    val nextIntent = when {
                        !onboardingSeen ->
                            Intent(this@SplashActivity, OnboardingActivity::class.java)

                        store.isLoggedIn() ->
                            Intent(this@SplashActivity, HomeActivity::class.java)

                        else ->
                            Intent(this@SplashActivity, AuthActivity::class.java)
                    }

                    goOnce(nextIntent)
                }
            }
        }
    }
}
