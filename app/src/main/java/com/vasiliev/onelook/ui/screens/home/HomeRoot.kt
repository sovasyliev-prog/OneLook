package com.vasiliev.onelook.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.screens.add.AddActivityScreen
import com.vasiliev.onelook.ui.screens.add.AddSupplementScreen
import com.vasiliev.onelook.ui.screens.add.ChooseTaskCategoryDialog
import com.vasiliev.onelook.ui.screens.notifications.NotificationSuccessScreen
import com.vasiliev.onelook.ui.screens.notifications.NotificationsScreen
import com.vasiliev.onelook.ui.screens.settings.BandHowToSyncScreen
import com.vasiliev.onelook.ui.screens.settings.BandNotSyncedScreen
import com.vasiliev.onelook.ui.screens.settings.SettingsScreen
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText

private enum class HomeTab { HOME, NOTIFICATIONS, SETTINGS }
private enum class Overlay { NONE, CHOOSE_CATEGORY, ADD_SUPPLEMENT, ADD_ACTIVITY, BAND_NOT_SYNCED, BAND_HOW_TO, NOTIF_SUCCESS }

@Composable
fun HomeRoot() {
    var tab by remember { mutableStateOf(HomeTab.HOME) }
    var overlay by remember { mutableStateOf(Overlay.NONE) }

    Surface(color = AppColors.White) {
        Box(Modifier.fillMaxSize()) {

            // --- Main content (3 tabs) ---
            Column(Modifier.fillMaxSize()) {
                Box(Modifier.weight(1f)) {
                    when (tab) {
                        HomeTab.HOME -> HomeScreen(
                            onAddClick = { overlay = Overlay.CHOOSE_CATEGORY },
                            onOpenNotifications = { tab = HomeTab.NOTIFICATIONS },
                            onOpenSettings = { tab = HomeTab.SETTINGS },
                        )
                        HomeTab.NOTIFICATIONS -> NotificationsScreen(
                            onClose = { tab = HomeTab.HOME },
                            onShowSuccess = { overlay = Overlay.NOTIF_SUCCESS }
                        )
                        HomeTab.SETTINGS -> SettingsScreen(
                            onClose = { tab = HomeTab.HOME },
                            onOpenBandFlow = { overlay = Overlay.BAND_NOT_SYNCED }
                        )
                    }
                }

                BottomBar(
                    active = tab,
                    onHome = { tab = HomeTab.HOME },
                    onAdd = { overlay = Overlay.CHOOSE_CATEGORY },
                    onNotifications = { tab = HomeTab.NOTIFICATIONS },
                    onSettings = { tab = HomeTab.SETTINGS },
                )
            }

            // --- Overlays / modals / flows ---
            when (overlay) {
                Overlay.CHOOSE_CATEGORY -> ChooseTaskCategoryDialog(
                    onDismiss = { overlay = Overlay.NONE },
                    onActivity = { overlay = Overlay.ADD_ACTIVITY },
                    onSupplement = { overlay = Overlay.ADD_SUPPLEMENT }
                )

                Overlay.ADD_SUPPLEMENT -> AddSupplementScreen(
                    onClose = { overlay = Overlay.NONE },
                    onDone = { overlay = Overlay.NONE }
                )

                Overlay.ADD_ACTIVITY -> AddActivityScreen(
                    onClose = { overlay = Overlay.NONE },
                    onDone = { overlay = Overlay.NONE }
                )

                Overlay.BAND_NOT_SYNCED -> BandNotSyncedScreen(
                    onClose = { overlay = Overlay.NONE },
                    onHowToFix = { overlay = Overlay.BAND_HOW_TO }
                )

                Overlay.BAND_HOW_TO -> BandHowToSyncScreen(
                    onClose = { overlay = Overlay.NONE },
                    onGoToSettings = { overlay = Overlay.NONE }, // тут потім підв’яжеш системні налаштування
                    onOpenHelpWebsite = { /* TODO: open url */ }
                )

                Overlay.NOTIF_SUCCESS -> NotificationSuccessScreen(
                    onClose = { overlay = Overlay.NONE },
                    onBackHome = {
                        overlay = Overlay.NONE
                        tab = HomeTab.HOME
                    }
                )

                Overlay.NONE -> Unit
            }
        }
    }
}

@Composable
private fun BottomBar(
    active: HomeTab,
    onHome: () -> Unit,
    onAdd: () -> Unit,
    onNotifications: () -> Unit,
    onSettings: () -> Unit,
) {
    // Як у Figma: біла панель, округлення, іконки + центральна кнопка
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(AppColors.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomItem(
            icon = R.drawable.ic_home,
            label = null,
            selected = active == HomeTab.HOME,
            onClick = onHome
        )

        BottomItem(
            icon = R.drawable.ic_bell,
            label = null,
            selected = active == HomeTab.NOTIFICATIONS,
            onClick = onNotifications
        )

        AddCenterButton(onClick = onAdd)

        BottomItem(
            icon = R.drawable.ic_settings,
            label = null,
            selected = active == HomeTab.SETTINGS,
            onClick = onSettings
        )
    }
}

@Composable
private fun BottomItem(
    icon: Int,
    label: String?,
    selected: Boolean,
    onClick: () -> Unit,
) {
    val tint = if (selected) AppColors.PurplePlum else AppColors.DarkGrey

    Box(
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(if (selected) AppColors.VioletLight else AppColors.White)
            .padding(10.dp)
            .clickable { onClick },
        contentAlignment = Alignment.Center
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = label,
            tint = tint
        )
    }
}

@Composable
private fun AddCenterButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(52.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(AppColors.PurplePlum)
            .clickable { onClick },
        contentAlignment = Alignment.Center
    ) {

        Text(text = "+", style = AppText.H2, color = AppColors.White)
    }
}
