package com.vasiliev.onelook.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun ResetSuccessScreen(
    onGoToLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Ілюстрацію можеш додати Image(painterResource(...)) якщо є asset
        Text(
            text = "Your password has\nbeen changed",
            style = AppText.H3,
            color = AppColors.DeepBlue
        )

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = "Log in",
            onClick = onGoToLogin,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
