package com.vasiliev.onelook.ui.screens.activity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

@Composable
fun WalkingActivityScreen(
    onBackHome: () -> Unit,
    onSaveSession: (durationSeconds: Int, steps: Int, calories: Double) -> Unit
) {
    var elapsedSeconds by rememberSaveable { mutableStateOf(0) }
    var running by rememberSaveable { mutableStateOf(true) }
    var showFinishDialog by rememberSaveable { mutableStateOf(false) }
    var finished by rememberSaveable { mutableStateOf(false) }

    val steps = remember(elapsedSeconds) {
        (elapsedSeconds * 1.3f).roundToInt()
    }
    val calories = remember(steps) {
        steps * 0.04
    }

    LaunchedEffect(running, finished) {
        while (running && !finished) {
            delay(1000)
            elapsedSeconds += 1
        }
    }

    if (finished) {
        WalkingSummaryScreen(
            elapsedSeconds = elapsedSeconds,
            steps = steps,
            calories = calories,
            onBackHome = onBackHome
        )
        return
    }

    WalkingTimerScreen(
        elapsedSeconds = elapsedSeconds,
        running = running,
        onToggleRunning = { running = !running },
        onFinishClick = {
            running = false
            showFinishDialog = true
        }
    )

    if (showFinishDialog) {
        AlertDialog(
            onDismissRequest = {
                showFinishDialog = false
                running = true
            },
            title = { Text("Finish walk?") },
            text = { Text("Are you sure you want to finish the walk?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showFinishDialog = false
                        onSaveSession(elapsedSeconds, steps, calories)
                        finished = true
                    }
                ) {
                    Text("Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showFinishDialog = false
                        running = true
                    }
                ) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
private fun WalkingTimerScreen(
    elapsedSeconds: Int,
    running: Boolean,
    onToggleRunning: () -> Unit,
    onFinishClick: () -> Unit
) {
    Surface(color = AppColors.White) {
        Box(Modifier.fillMaxSize()) {
            ActivityBackground()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppSpacing.ScreenPadding)
                    .padding(top = AppSpacing.L, bottom = AppSpacing.XXL),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(title = "Walking", onFinishClick = onFinishClick)

                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_walk),
                    contentDescription = null,
                    tint = AppColors.PurplePlum,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.height(AppSpacing.S))

                Text("Walking", style = AppText.Body3, color = AppColors.DeepBlue)

                Spacer(Modifier.height(AppSpacing.S))

                Text(
                    text = formatDuration(elapsedSeconds),
                    style = AppText.H1,
                    color = AppColors.DeepBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.weight(1f))

                ActivityProgress(elapsedSeconds)

                Spacer(Modifier.height(AppSpacing.L))

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(AppColors.PurplePlum)
                        .clickable { onToggleRunning() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(if (running) R.drawable.ic_pause else R.drawable.ic_play),
                        contentDescription = if (running) "Pause" else "Play",
                        tint = AppColors.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun WalkingSummaryScreen(
    elapsedSeconds: Int,
    steps: Int,
    calories: Double,
    onBackHome: () -> Unit
) {
    Surface(color = AppColors.White) {
        Box(Modifier.fillMaxSize()) {
            ActivityBackground()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = AppSpacing.ScreenPadding)
                    .padding(top = AppSpacing.L, bottom = AppSpacing.XXL),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(title = "Walking", onFinishClick = onBackHome)

                Spacer(Modifier.weight(1f))

                Image(
                    painter = painterResource(R.drawable.ill_goal_achieved),
                    contentDescription = null,
                    modifier = Modifier.size(176.dp)
                )

                Spacer(Modifier.height(AppSpacing.L))

                Text(
                    text = "You have finished\nWalking",
                    style = AppText.H3,
                    color = AppColors.DeepBlue,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(AppSpacing.L))

                SummaryRow(label = "Time", value = formatDuration(elapsedSeconds))
                SummaryRow(label = "Steps", value = steps.toString())
                SummaryRow(label = "Calories", value = "%.1f".format(calories))

                Spacer(Modifier.weight(1f))

                PrimaryButton(
                    text = "Back to Home",
                    onClick = onBackHome,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun TopBar(title: String, onFinishClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(title, style = AppText.H3, color = AppColors.DeepBlue)
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(AppColors.LilacPetalsDark)
                .clickable { onFinishClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                tint = AppColors.DeepBlue,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ActivityBackground() {
    Canvas(Modifier.fillMaxSize()) {
        drawCircle(
            color = AppColors.VioletLight.copy(alpha = 0.45f),
            radius = size.minDimension * 0.25f,
            center = Offset(-size.width * 0.03f, size.height * 0.17f),
            style = Stroke(width = 28.dp.toPx())
        )
        drawCircle(
            color = AppColors.PeachLight,
            radius = size.minDimension * 0.14f,
            center = Offset(size.width * 0.02f, size.height * 0.72f)
        )
        drawCircle(
            color = AppColors.WaterLight,
            radius = size.minDimension * 0.24f,
            center = Offset(size.width * 0.95f, size.height * 0.95f)
        )
        drawArc(
            color = AppColors.SunnyYellowLight,
            startAngle = 30f,
            sweepAngle = 55f,
            useCenter = true,
            topLeft = Offset(size.width * 0.74f, size.height * 0.29f),
            size = androidx.compose.ui.geometry.Size(90.dp.toPx(), 90.dp.toPx())
        )
    }
}

@Composable
private fun ActivityProgress(elapsedSeconds: Int) {
    val progress = ((elapsedSeconds % 300) / 300f).coerceIn(0f, 1f)

    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("0:00", style = AppText.Body3, color = AppColors.DustGrey)
            Text("5:00", style = AppText.Body3, color = AppColors.DustGrey)
        }
        Spacer(Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(AppColors.WaterLight)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(6.dp))
                    .background(AppColors.PurplePlum)
            )
        }
    }
}

@Composable
private fun SummaryRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(AppColors.LilacPetals)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_walk),
            contentDescription = null,
            tint = AppColors.PurplePlum,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text(label, style = AppText.Body3, color = AppColors.DarkGrey, modifier = Modifier.weight(1f))
        Text(value, style = AppText.Body2, color = AppColors.DeepBlue)
    }
    Spacer(Modifier.height(AppSpacing.S))
}

private fun formatDuration(totalSeconds: Int): String {
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
