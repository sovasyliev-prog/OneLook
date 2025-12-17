package com.vasiliev.onelook.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color

private val Mint = Color(0xFF7FDCC6)

@Composable
fun HomeScreen(
    onAddClick: () -> Unit,
    onOpenNotifications: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    Surface(color = AppColors.White) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding),
            contentPadding = PaddingValues(top = AppSpacing.L, bottom = 120.dp)
        ) {
            item {
                TopRow(onOpenNotifications = onOpenNotifications, onOpenSettings = onOpenSettings)
                Spacer(Modifier.height(AppSpacing.L))
                Text(text = "Hi Madison!", style = AppText.H2, color = AppColors.DeepBlue)
                Spacer(Modifier.height(AppSpacing.M))
            }

            item {
                HealthStatsRow()
                Spacer(Modifier.height(AppSpacing.L))
            }

            item {
                TodoHeader(onAddClick = onAddClick)
                Spacer(Modifier.height(AppSpacing.S))
            }

            item { TodoItem(title = "Breath training", subtitle = "Continue exercise", checked = true) }
            item { Spacer(Modifier.height(AppSpacing.S)) }
            item { TodoItem(title = "Omega 3", subtitle = "1 pill after meal", checked = true) }
            item { Spacer(Modifier.height(AppSpacing.S)) }
            item { TodoItem(title = "Vitamin D", subtitle = "1 sachet before meal", checked = false) }
            item { Spacer(Modifier.height(AppSpacing.S)) }
            item { TodoItem(title = "Step Goal", subtitle = "4 456 / 10 000", checked = false, showProgress = true) }
        }
    }
}

@Composable
private fun TopRow(onOpenNotifications: () -> Unit, onOpenSettings: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        // logo
        Image(
            painter = painterResource(R.drawable.ic_onelook_logo),
            contentDescription = null,
            modifier = Modifier.size(28.dp)
        )
        Spacer(Modifier.weight(1f))

        IconChip(icon = R.drawable.ic_bell, onClick = onOpenNotifications)
        Spacer(Modifier.width(10.dp))
        IconChip(icon = R.drawable.ic_settings, onClick = onOpenSettings)
    }
}

@Composable
private fun IconChip(icon: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(34.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(AppColors.VioletLight)
            .padding(8.dp)
            .clickable { onClick },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = AppColors.DeepBlue
        )
    }
}

@Composable
private fun HealthStatsRow() {
    Column {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Text("Health stats", style = AppText.H3, color = AppColors.DeepBlue)
            Spacer(Modifier.weight(1f))
            Text("Weekly", style = AppText.Body3, color = AppColors.DarkGrey)
        }
        Spacer(Modifier.height(AppSpacing.S))
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard(title = "Breath Rate", value = "12 BrPM", bg = AppColors.VioletLight, icon = R.drawable.ic_breath)
            StatCard(title = "Heart Rate", value = "68 BPM", bg = Mint, icon = R.drawable.ic_heart)
            StatCard(title = "Blood Pr.", value = "122 / 8", bg = AppColors.PeachLight, icon = R.drawable.ic_blood)
        }
    }
}

@Composable
private fun StatCard(title: String, value: String, bg: androidx.compose.ui.graphics.Color, icon: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(bg)
            .padding(12.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(bg)
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = AppColors.DeepBlue,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.height(10.dp))
        Text(title, style = AppText.Body3, color = AppColors.DarkGrey)
        Spacer(Modifier.height(6.dp))
        Text(value, style = AppText.H3, color = AppColors.DeepBlue)
    }
}

@Composable
private fun TodoHeader(onAddClick: () -> Unit) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text("To-do list", style = AppText.H3, color = AppColors.DeepBlue)
        Spacer(Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(AppColors.VioletLight)
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .clickable { onAddClick },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = null,
                tint = AppColors.PurplePlum,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text("Add task", style = AppText.Body3, color = AppColors.PurplePlum)
        }
    }
}

@Composable
private fun TodoItem(title: String, subtitle: String, checked: Boolean, showProgress: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(AppColors.White)
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(if (checked) R.drawable.ic_check_on else R.drawable.ic_check_off),
            contentDescription = null,
            tint = if (checked) Mint else AppColors.DarkGrey,
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(4.dp))
            Text(subtitle, style = AppText.Body3, color = AppColors.DarkGrey)
            if (showProgress) {
                Spacer(Modifier.height(8.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(AppColors.VioletLight)
                )
            }
        }
        Spacer(Modifier.width(10.dp))
        Icon(
            painter = painterResource(R.drawable.ic_chevron_right),
            contentDescription = null,
            tint = AppColors.DarkGrey,
            modifier = Modifier.size(18.dp)
        )
    }
}
