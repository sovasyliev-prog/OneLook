package com.vasiliev.onelook.ui.screens.add

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
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun AddActivityScreen(
    onClose: () -> Unit,
    onDone: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            TopBar(title = "Add Activity", onClose = onClose)

            Spacer(Modifier.height(AppSpacing.M))
            Text("Choose the type of activity", style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(AppSpacing.S))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActivityChip(icon = R.drawable.ic_run, text = "Running")
                ActivityChip(icon = R.drawable.ic_walk, text = "Walking")
                ActivityChip(icon = R.drawable.ic_fitness, text = "Fitness")
                ActivityChip(icon = R.drawable.ic_yoga, text = "Yoga")
            }

            Spacer(Modifier.height(AppSpacing.M))
            Text("Time of day", style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(AppSpacing.S))

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                ActivityChip(icon = R.drawable.ic_morning, text = "Morning")
                ActivityChip(icon = R.drawable.ic_afternoon, text = "Afternoon")
                ActivityChip(icon = R.drawable.ic_evening, text = "Evening")
                ActivityChip(icon = R.drawable.ic_night, text = "Night")
            }

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Add Activity",
                onClick = onDone,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(AppSpacing.XXL))
        }
    }
}

@Composable
private fun TopBar(title: String, onClose: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = AppSpacing.L),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(30.dp)
                .clickable {  onClose }
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.VioletLight),

            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                tint = AppColors.DeepBlue
            )
        }
    }
    Spacer(Modifier.height(AppSpacing.S))
    Text(title, style = AppText.H3, color = AppColors.DeepBlue)
}

@Composable
private fun ActivityChip(icon: Int, text: String) {
    Column(
        modifier = Modifier
            .width(78.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.VioletLight)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(icon), contentDescription = null, modifier = Modifier.size(18.dp))
        Spacer(Modifier.height(6.dp))
        Text(text, style = AppText.Body3, color = AppColors.DeepBlue)
    }
}
