package com.vasiliev.onelook.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText
import com.vasiliev.onelook.util.isValidEmail

@Composable
fun ResetEmailScreen(
    onBackToLogin: () -> Unit,
    onEmailConfirmed: (String) -> Unit
) {
    val context = LocalContext.current
    val store = remember { LocalAuthStore(context) }

    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter your e-mail",
            style = AppText.H2,
            color = AppColors.DeepBlue
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "You will receive a link to confirm the password change to the e-mail address provided",
            style = AppText.Body3,
            color = AppColors.DarkGrey
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = "Confirm e-mail",
            onClick = {
                val okEmail = isValidEmail(email)
                val saved = store.getUser()

                if (!okEmail) {
                    Toast.makeText(context, "Some fields has incorrect data", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                if (saved == null || saved.email != email.trim()) {
                    Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                // Імітація "лист на пошту" — просто переходимо далі
                onEmailConfirmed(email.trim())
            }
        )

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Back to login")
        }
    }
}
