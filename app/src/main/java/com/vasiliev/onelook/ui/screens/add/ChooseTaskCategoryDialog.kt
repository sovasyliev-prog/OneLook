package com.vasiliev.onelook.ui.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun ChooseTaskCategoryDialog(
    onDismiss: () -> Unit,
    onActivity: () -> Unit,
    onSupplement: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            color = AppColors.White
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Choose task category", style = AppText.H3, color = AppColors.DeepBlue)
                Spacer(Modifier.height(AppSpacing.M))

                CategoryButton("Activity", onClick = onActivity)
                Spacer(Modifier.height(AppSpacing.S))
                CategoryButton("Supplement", onClick = onSupplement)
            }
        }
    }
}

@Composable
private fun CategoryButton(text: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(AppColors.VioletLight)
            .clickable { onClick() }
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text, style = AppText.Body2, color = AppColors.PurplePlum)
    }
}
