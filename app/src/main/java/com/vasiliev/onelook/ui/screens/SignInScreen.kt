package com.vasiliev.onelook.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.HomeActivity
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText

@Composable
fun SignInScreen(
    onGoToSignUp: () -> Unit,
    onForgotPassword: () -> Unit
)
 {
    val context = LocalContext.current
    val store = remember { LocalAuthStore(context) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            text = "Log In",
            onClick = {
                val user = store.getUser()

                if (user == null || user.email != email || user.password != password) {
                    Toast.makeText(
                        context,
                        "Email or password incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    store.setLoggedIn(true)
                    context.startActivity(
                        Intent(context, HomeActivity::class.java)
                    )
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
            Text("Don’t have an account? Sign up")
        }
    }
}
