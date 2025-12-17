package com.vasiliev.onelook.activities.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.theme.AppTheme

class ChooseActivityTypeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                ChooseActivityTypeScreen(
                    onClose = { finish() },
                    onStart = { selected ->
                        // TODO: navigate to AddActivityScreen
                        // selected = обраний тип активності
                        finish()
                    }
                )
            }
        }
    }
}

/* ----------------------------- UI ----------------------------- */

@Composable
private fun ChooseActivityTypeScreen(
    onClose: () -> Unit,
    onStart: (ActivityType) -> Unit
) {
    var selectedType by remember { mutableStateOf<ActivityType?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        /* -------- Header -------- */
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onClose() }
            )
        }

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Start Activity",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Choose the type of activity",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(Modifier.height(24.dp))

        /* -------- Grid -------- */
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ActivityType.entries) { type ->
                ActivityTypeItem(
                    type = type,
                    selected = selectedType == type,
                    onClick = { selectedType = type }
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        /* -------- Start Button -------- */
        Button(
            onClick = { selectedType?.let { onStart(it) } },
            enabled = selectedType != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Start now")
        }
    }
}

/* ----------------------------- Grid Item ----------------------------- */

@Composable
private fun ActivityTypeItem(
    type: ActivityType,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(
                if (selected) Color(0xFFEDEBFF) else Color.Transparent
            )
            .clickable { onClick() }
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape)
                .background(
                    if (selected) Color(0xFF6C63FF) else Color(0xFFF4F4F4)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(type.icon),
                contentDescription = type.title,
                tint = if (selected) Color.White else Color.DarkGray,
                modifier = Modifier.size(28.dp)
            )
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = type.title,
            fontSize = 12.sp
        )
    }
}

/* ----------------------------- Model ----------------------------- */

private enum class ActivityType(
    val title: String,
    val icon: Int
) {
    RUNNING("Running", R.drawable.ic_run),
    WALKING("Walking", R.drawable.ic_walk),
    FITNESS("Fitness", R.drawable.ic_fitness),
    YOGA("Yoga", R.drawable.ic_yoga),
    ROLLERS("Rollers", R.drawable.ic_rollers),
    BREATHING("Breathing", R.drawable.ic_close)
}
