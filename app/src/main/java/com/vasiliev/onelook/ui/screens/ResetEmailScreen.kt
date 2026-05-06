package com.vasiliev.onelook.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.auth.AuthRepository
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText
import com.vasiliev.onelook.util.isValidEmail
import kotlinx.coroutines.launch

@Composable
fun ResetEmailScreen(
    onBackToLogin: () -> Unit,
    onEmailConfirmed: (String) -> Unit
) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository() }
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

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
            text = if (loading) "Sending..." else "Confirm e-mail",
            onClick = {
                if (loading) return@PrimaryButton

                if (!isValidEmail(email)) {
                    Toast.makeText(context, "Some fields has incorrect data", Toast.LENGTH_SHORT).show()
                    return@PrimaryButton
                }

                scope.launch {
                    loading = true
                    val result = authRepository.sendPasswordReset(email)
                    loading = false

                    result.onSuccess {
                        Toast.makeText(context, "Password reset email was sent", Toast.LENGTH_SHORT).show()
                        onEmailConfirmed(email.trim())
                    }.onFailure {
                        Toast.makeText(
                            context,
                            it.message ?: "Password reset email was not sent",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )

        Spacer(Modifier.height(12.dp))

        TextButton(onClick = onBackToLogin) {
            Text("Back to login")
        }
    }
}
