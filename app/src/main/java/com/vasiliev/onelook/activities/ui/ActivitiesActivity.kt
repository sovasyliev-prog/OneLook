package com.vasiliev.onelook.activities.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.activities.viewmodel.ActivitiesViewModel
import com.vasiliev.onelook.ui.theme.AppTheme

class ActivitiesActivity : ComponentActivity() {

    private val vm by viewModels<ActivitiesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val activities by vm.activities.collectAsState()

            Column(Modifier.fillMaxSize().padding(16.dp)) {

                Button(
                    onClick = {
                        startActivity(
                            Intent(this@ActivitiesActivity, ChooseActivityTypeActivity::class.java)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Start activity now")
                }

                Spacer(Modifier.height(16.dp))

                LazyColumn {
                    items(activities) {
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}
