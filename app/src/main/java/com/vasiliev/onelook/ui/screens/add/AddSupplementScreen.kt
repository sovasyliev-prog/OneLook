package com.vasiliev.onelook.ui.screens.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
fun AddSupplementScreen(
    onClose: () -> Unit,
    onDone: () -> Unit
) {
    Surface(color = AppColors.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            TopBar(title = "Add Supplement", onClose = onClose)

            Spacer(Modifier.height(AppSpacing.M))

            // Тут можна підв’язати свої TextField компоненти з Figma.
            FakeField(label = "Supplement Name", hint = "Type name of the supplement")
            Spacer(Modifier.height(AppSpacing.M))

            Text("Supplement Form", style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(AppSpacing.S))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                PillChip(icon = R.drawable.ic_pill, text = "Pill")
                PillChip(icon = R.drawable.ic_tablet, text = "Tablet")
                PillChip(icon = R.drawable.ic_sachet, text = "Sachet")
                PillChip(icon = R.drawable.ic_drops, text = "Drops")
            }

            Spacer(Modifier.height(AppSpacing.M))
            Text("Dosage", style = AppText.Body2, color = AppColors.DeepBlue)
            Spacer(Modifier.height(AppSpacing.S))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                repeat(6) { i -> SmallCircle(text = (i + 1).toString(), selected = i == 0) }
            }

            Spacer(Modifier.weight(1f))

            PrimaryButton(
                text = "Add Supplement",
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
                .clip(RoundedCornerShape(10.dp))
                .clickable { onClose }
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
private fun FakeField(label: String, hint: String) {
    Column {
        Text(label, style = AppText.Body3, color = AppColors.DarkGrey)
        Spacer(Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(AppColors.White)
                .padding(14.dp)
        ) {
            Text(hint, style = AppText.Body3, color = AppColors.DarkGrey)
        }
    }
}

@Composable
private fun PillChip(icon: Int, text: String) {
    Column(
        modifier = Modifier
            .width(68.dp)
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

@Composable
private fun SmallCircle(text: String, selected: Boolean) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(if (selected) AppColors.PurplePlum else AppColors.VioletLight),
        contentAlignment = Alignment.Center
    ) {
        Text(text, style = AppText.Body3, color = if (selected) AppColors.White else AppColors.DeepBlue)
    }
}
