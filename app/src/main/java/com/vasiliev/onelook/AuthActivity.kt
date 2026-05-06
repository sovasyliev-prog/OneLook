package com.vasiliev.onelook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vasiliev.onelook.ui.screens.ResetEmailScreen
import com.vasiliev.onelook.ui.screens.ResetSuccessScreen
import com.vasiliev.onelook.ui.screens.SignInScreen
import com.vasiliev.onelook.ui.screens.SignUpScreen
import com.vasiliev.onelook.ui.theme.AppTheme

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val nav = rememberNavController()
                NavHost(navController = nav, startDestination = "signin") {
                    composable("signin") {
                        SignInScreen(
                            onGoToSignUp = { nav.navigate("signup") },
                            onForgotPassword = { nav.navigate("reset_email") }
                        )
                    }

                    composable("signup") {
                        SignUpScreen(onGoToSignIn = { nav.popBackStack() })
                    }

                    composable("reset_email") {
                        ResetEmailScreen(
                            onBackToLogin = { nav.popBackStack("signin", inclusive = false) },
                            onEmailConfirmed = { nav.navigate("reset_success") }
                        )
                    }

                    composable("reset_success") {
                        ResetSuccessScreen(
                            onGoToLogin = { nav.popBackStack("signin", inclusive = false) }
                        )
                    }
                }
            }
        }
    }
}
