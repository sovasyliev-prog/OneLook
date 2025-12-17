package com.vasiliev.onelook.ui.screens.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun NotificationSuccessScreen(
    onClose: () -> Unit,
    onBackHome: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(AppSpacing.L))
            // close (reuse drawable)
            // Можеш зробити як у band flow — зараз просто “ховаємо”
            Spacer(Modifier.height(AppSpacing.L))

            Image(
                painter = painterResource(R.drawable.ill_goal_achieved),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 260.dp, max = 340.dp)
            )

            Spacer(Modifier.height(AppSpacing.L))

            Text(
                text = "You have achieved your Step\nGoal for today",
                style = AppText.H3,
                color = AppColors.DeepBlue,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Back to Home",
                onClick = onBackHome,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(AppSpacing.XXL))
        }
    }
}
