package com.vasiliev.onelook.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextAlign



@Composable
fun SplashScreen(
    onStartClick: () -> Unit
) {
    Surface(color = AppColors.LilacPetals) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f))

            // Лого (поклади png/svg у drawable)
            Image(
                painter = painterResource(R.drawable.ic_onelook_logo),
                contentDescription = "OneLook Logo",
                modifier = Modifier.size(96.dp)
            )

            Spacer(Modifier.height(AppSpacing.L))

            Text(
                text = "Welcome to\nOneLook",
                style = AppText.H2,
                color = AppColors.DeepBlue,
                textAlign = TextAlign.Center,          // ← вирівнювання рядків
                modifier = Modifier
                    .fillMaxWidth()                    // ← простір для центрування
                    .padding(horizontal = 24.dp)       // ← padding як у grid
            )


            Spacer(Modifier.height(AppSpacing.S))

            Text(
                text = "Just take a look and take action!",
                style = AppText.Body3,
                color = AppColors.DarkGrey
            )

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Let’s start",
                onClick = onStartClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )


            Spacer(Modifier.height(AppSpacing.XXL))
        }
    }
}
