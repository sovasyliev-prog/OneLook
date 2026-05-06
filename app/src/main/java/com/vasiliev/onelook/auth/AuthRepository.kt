package com.vasiliev.onelook.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepository(
    private val firebaseAuth: FirebaseAuth? = defaultFirebaseAuth()
) {

    fun isAuthorized(): Boolean = firebaseAuth?.currentUser != null

    suspend fun signIn(email: String, password: String): Result<Unit> = runCatching {
        auth().signInWithEmailAndPassword(email.trim(), password).await()
    }

    suspend fun signUp(fullName: String, email: String, password: String): Result<Unit> = runCatching {
        val auth = auth()
        auth.createUserWithEmailAndPassword(email.trim(), password).await()
        val profile = UserProfileChangeRequest.Builder()
            .setDisplayName(fullName.trim())
            .build()
        auth.currentUser?.updateProfile(profile)?.await()
    }

    suspend fun sendPasswordReset(email: String): Result<Unit> = runCatching {
        auth().sendPasswordResetEmail(email.trim()).await()
    }

    fun signOut() {
        firebaseAuth?.signOut()
    }

    private fun auth(): FirebaseAuth {
        return firebaseAuth ?: error(
            "Firebase is not configured. Add app/google-services.json from Firebase Console."
        )
    }

    companion object {
        private fun defaultFirebaseAuth(): FirebaseAuth? {
            return try {
                FirebaseAuth.getInstance()
            } catch (_: IllegalStateException) {
                null
            }
        }
    }
}
