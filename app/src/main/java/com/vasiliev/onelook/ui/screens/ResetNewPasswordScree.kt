package com.vasiliev.onelook.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText
import com.vasiliev.onelook.util.isValidPassword

@Composable
fun ResetNewPasswordScreen(
    email: String,
    onPasswordChanged: () -> Unit
) {
    val context = LocalContext.current
    val store = remember { LocalAuthStore(context) }

    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Enter a new\npassword",
            style = AppText.H2,
            color = AppColors.DeepBlue
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        // Мінімальна підказка як на макеті
        Text("• At least 8 characters", style = AppText.Body3, color = AppColors.DarkGrey)

        Spacer(Modifier.height(24.dp))

        PrimaryButton(
            text = "Confirm password",
            onClick = {
                val saved = store.getUser()

                if (saved == null || saved.email != email) {
                    Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                if (!isValidPassword(password)) {
                    Toast.makeText(context, "Some fields has incorrect data", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                store.updatePassword(password)
                store.setLoggedIn(false) // після зміни паролю логічно зняти авторизацію
                onPasswordChanged()
            }
        )
    }
}
