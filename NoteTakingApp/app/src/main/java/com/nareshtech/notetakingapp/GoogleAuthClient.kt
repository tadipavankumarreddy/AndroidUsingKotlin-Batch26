package com.nareshtech.notetakingapp

import android.content.Context
import android.provider.Settings.Global.getString
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.firebase.auth.FirebaseAuth

class GoogleAuthClient(
    private val context: Context,
) {
    private val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    // Instantiate a Google sign-in request
    val googleIdOption = GetGoogleIdOption.Builder()
        // Your server's client ID, not your Android client ID.
        .setServerClientId(getString(R.string.default_web_client_id))
        // Only show accounts previously used to sign in.
        .setFilterByAuthorizedAccounts(true)
        .build()

    // Create the Credential Manager request
    val request = GetCredentialRequest.Builder()
        .addCredentialOption(googleIdOption)
        .build()
}