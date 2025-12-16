package com.vasiliev.onelook.data

import android.content.Context
import android.content.SharedPreferences

data class LocalUser(
    val fullName: String,
    val email: String,
    val password: String
)

class LocalAuthStore(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("onelook_prefs", Context.MODE_PRIVATE)

    fun saveUser(user: LocalUser) {
        prefs.edit()
            .putString("full_name", user.fullName)
            .putString("email", user.email)
            .putString("password", user.password)
            .apply()
    }

    fun getUser(): LocalUser? {
        val fullName = prefs.getString("full_name", null) ?: return null
        val email = prefs.getString("email", null) ?: return null
        val password = prefs.getString("password", null) ?: return null
        return LocalUser(fullName, email, password)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("logged_in", isLoggedIn).apply()
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("logged_in", false)

    fun logout() {
        setLoggedIn(false)
    }

    fun updatePassword(newPassword: String) {
        prefs.edit().putString("password", newPassword).apply()
    }

}
