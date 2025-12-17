package com.vasiliev.onelook.ui.screens.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color

private val Mint = Color(0xFF7FDCC6)


@Composable
fun NotificationsScreen(
    onClose: () -> Unit,
    onShowSuccess: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppSpacing.L),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_left),
                    contentDescription = "Close",
                    tint = AppColors.DeepBlue,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onClose() }
                )
                Text("Notifications", style = AppText.H3, color = AppColors.DeepBlue)
                Spacer(Modifier.weight(1f))
                // (optional) demo btn to open success
                Icon(
                    painter = painterResource(R.drawable.ic_check_on),
                    contentDescription = "Success",
                    tint = Mint, // або твій Mint
                    modifier = Modifier
                        .size(22.dp)
                        .clickable { onShowSuccess() }
                )

            }

            Spacer(Modifier.height(AppSpacing.M))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 120.dp)
            ) {
                item { NotificationCard("You have achieved your Step Goal for today", "today") }
                item { Spacer(Modifier.height(AppSpacing.S)) }
                item { NotificationCard("Remember to take your daily dose of Vitamin D", "1 min ago") }
                item { Spacer(Modifier.height(AppSpacing.S)) }
                item { NotificationCard("New Weekly Report is available in Progress section", "yesterday") }
                item { Spacer(Modifier.height(AppSpacing.S)) }
                item { NotificationCard("Finish your Breathing Training. Just 49% left for today", "yesterday") }
            }
        }
    }
}

@Composable
private fun NotificationCard(title: String, time: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(AppColors.White)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_notification_dot),
            contentDescription = null,
            tint = AppColors.PurplePlum,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(4.dp))
            Text(time, style = AppText.Body3, color = AppColors.DarkGrey)
        }
    }
}
