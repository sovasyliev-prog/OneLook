package com.vasiliev.onelook.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


import androidx.compose.material3.*

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = AppColors.PurplePlum,
            onPrimary = AppColors.White,
            background = AppColors.LilacPetals,
            onBackground = AppColors.DeepBlue,
            surface = AppColors.White,
            onSurface = AppColors.DeepBlue,
            error = AppColors.Alert
        ),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}
