package com.vasiliev.onelook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vasiliev.onelook.ui.screens.home.HomeRoot
import com.vasiliev.onelook.ui.theme.AppTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                HomeRoot()
            }
        }
    }
}
