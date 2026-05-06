package com.vasiliev.onelook.ui.screens

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.HomeActivity
import com.vasiliev.onelook.auth.AuthRepository
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText
import com.vasiliev.onelook.util.isValidEmail
import kotlinx.coroutines.launch

@Composable
fun SignInScreen(
    onGoToSignUp: () -> Unit,
    onForgotPassword: () -> Unit
) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository() }
    val scope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    var googleEnabled by remember { mutableStateOf(true) }
    var facebookEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome back",
            style = AppText.H2,
            color = AppColors.DeepBlue
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        TextButton(
            onClick = onForgotPassword,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Forgot your password?")
        }

        Spacer(Modifier.height(16.dp))

        PrimaryButton(
            text = if (loading) "Logging in..." else "Log In",
            onClick = {
                if (loading) return@PrimaryButton

                if (!isValidEmail(email) || password.isBlank()) {
                    Toast.makeText(
                        context,
                        "Some fields has incorrect data",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@PrimaryButton
                }

                scope.launch {
                    loading = true
                    val result = authRepository.signIn(email, password)
                    loading = false

                    result.onSuccess {
                        context.startActivity(Intent(context, HomeActivity::class.java))
                        (context as? Activity)?.finish()
                    }.onFailure {
                        Toast.makeText(
                            context,
                            it.message ?: "Email or password incorrect",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        OutlinedButton(
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
                googleEnabled = false
            },
            enabled = googleEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue with Google")
        }

        Spacer(Modifier.height(8.dp))

        OutlinedButton(
            onClick = {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
                facebookEnabled = false
            },
            enabled = facebookEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Continue with Facebook")
        }

        Spacer(Modifier.height(16.dp))

        TextButton(onClick = onGoToSignUp) {
            Text("Don't have an account? Sign up")
        }
    }
}
