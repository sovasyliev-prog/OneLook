package com.vasiliev.onelook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

/* ===== SharedPreferences keys ===== */

private const val PREFS_NAME = "app_prefs"
private const val KEY_ONBOARDING_SEEN = "onboarding_seen"

/* ================================= */

@Composable
fun SplashScreen(
    onGoToOnboarding: () -> Unit,
    onGoToSignIn: () -> Unit
) {
    val context = LocalContext.current

    // ---- Splash logic (1 second) ----
    LaunchedEffect(Unit) {
        delay(1000)

        val prefs = context.getSharedPreferences(PREFS_NAME, android.content.Context.MODE_PRIVATE)
        val onboardingSeen = prefs.getBoolean(KEY_ONBOARDING_SEEN, false)

        if (onboardingSeen) {
            onGoToSignIn()
        } else {
            onGoToOnboarding()
        }
    }

    // -------- UI --------
    Surface(color = AppColors.LilacPetals) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.weight(1f))

            // Logo
            Image(
                painter = painterResource(R.drawable.ic_onelook_logo),
                contentDescription = "OneLook Logo",
                modifier = Modifier.size(96.dp)
            )

            Spacer(modifier = Modifier.height(AppSpacing.L))

            // Title (CENTERED, 2 lines)
            Text(
                text = "Welcome to\nOneLook",
                style = AppText.H2,
                color = AppColors.DeepBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(AppSpacing.S))

            // Subtitle
            Text(
                text = "Just take a look and take action!",
                style = AppText.Body3,
                color = AppColors.DarkGrey,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            // Start button
            PrimaryButton(
                text = "Let’s start",
                onClick = {
                    val prefs = context.getSharedPreferences(
                        PREFS_NAME,
                        android.content.Context.MODE_PRIVATE
                    )
                    val onboardingSeen = prefs.getBoolean(KEY_ONBOARDING_SEEN, false)

                    if (onboardingSeen) {
                        onGoToSignIn()
                    } else {
                        onGoToOnboarding()
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(AppSpacing.XXL))
        }
    }
}
