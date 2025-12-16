package com.vasiliev.onelook.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.vasiliev.onelook.data.LocalAuthStore
import com.vasiliev.onelook.data.LocalUser
import com.vasiliev.onelook.ui.components.PrimaryButton
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppText
import com.vasiliev.onelook.util.isValidEmail
import com.vasiliev.onelook.util.isValidFullName
import com.vasiliev.onelook.util.isValidPassword

@Composable
fun SignUpScreen(
    onGoToSignIn: () -> Unit
) {
    val context = LocalContext.current
    val store = remember { LocalAuthStore(context) }

    var fullName by remember { mutableStateOf("") }
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
            text = "Create an account",
            style = AppText.H2,
            color = AppColors.DeepBlue
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

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

        Spacer(Modifier.height(16.dp))

        PrimaryButton(
            text = "Sign Up",
            onClick = {
                val valid = isValidFullName(fullName)
                        && isValidEmail(email)
                        && isValidPassword(password)

                if (!valid) {
                    Toast.makeText(
                        context,
                        "Some fields has incorrect data",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    store.saveUser(LocalUser(fullName, email, password))
                    store.setLoggedIn(true)
                    Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                    onGoToSignIn()
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

        TextButton(onClick = onGoToSignIn) {
            Text("Already have an account? Log in")
        }
    }
}
