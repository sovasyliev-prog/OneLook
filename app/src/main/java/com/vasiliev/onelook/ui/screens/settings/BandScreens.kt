package com.vasiliev.onelook.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun BandNotSyncedScreen(
    onClose: () -> Unit,
    onHowToFix: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            TopRightClose(onClose)
            Spacer(Modifier.height(AppSpacing.L))

            Image(
                painter = painterResource(R.drawable.band_not_sync),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 260.dp, max = 340.dp)
            )

            Spacer(Modifier.height(AppSpacing.L))

            Text(
                text = "Your Watch is not\nsynchronized!",
                style = AppText.H3,
                color = AppColors.DeepBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "How to fix",
                onClick = onHowToFix,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(AppSpacing.XXL))
        }
    }
}

@Composable
fun BandHowToSyncScreen(
    onClose: () -> Unit,
    onGoToSettings: () -> Unit,
    onOpenHelpWebsite: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            TopRightClose(onClose)

            Spacer(Modifier.height(AppSpacing.L))

            Text(
                text = "How to synchronize\nyour Watch",
                style = AppText.H3,
                color = AppColors.DeepBlue,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(AppSpacing.L))

            StepItem(1, "Make sure the watch is turned on")
            StepItem(2, "Check your Bluetooth Settings")
            StepItem(3, "Try to find your Watch on the list of\navailable devices")
            StepItem(4, "Find Help on our website", trailingExternal = true, onTrailingClick = onOpenHelpWebsite)

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Go to Settings",
                onClick = onGoToSettings,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(AppSpacing.XXL))
        }
    }
}

@Composable
private fun TopRightClose(onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AppSpacing.L),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.VioletLight)
                .clickable { onClose() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                tint = AppColors.DeepBlue
            )
        }
    }
}

@Composable
private fun StepItem(
    number: Int,
    text: String,
    trailingExternal: Boolean = false,
    onTrailingClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = AppSpacing.S)
            .clip(RoundedCornerShape(14.dp))
            .background(AppColors.VioletLight)
            .padding(horizontal = 12.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(22.dp)
                .clip(RoundedCornerShape(11.dp))
                .background(AppColors.White),
            contentAlignment = Alignment.Center
        ) {
            Text(number.toString(), style = AppText.Body3, color = AppColors.DeepBlue)
        }

        Spacer(Modifier.width(12.dp))

        Text(text, style = AppText.Body3, color = AppColors.DeepBlue, modifier = Modifier.weight(1f))

        if (trailingExternal) {
            Spacer(Modifier.width(8.dp))
            Icon(
                painter = painterResource(R.drawable.ic_open_in_new), // з Figma
                contentDescription = "Open",
                tint = AppColors.DeepBlue,
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onTrailingClick?.invoke() }
            )
        }
    }
}
