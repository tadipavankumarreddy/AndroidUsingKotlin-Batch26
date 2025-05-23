package com.nareshtech.notetakingapp

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class GoogleAuthClient(private val context: Context) {

    /**
     * A new Jetpack API (as of Android 14+) to unify and simplify login experiences
     * It works with Google, Passkeys, and third party Identity providers.*/
    private val credentialManager = CredentialManager.create(context)
    private val firebaseAuth = FirebaseAuth.getInstance()

    // We are going to manage the entire sign in, sign out processes
    // This is for signIn
    suspend fun signIn(): Boolean{
        if(isSignedIn()){
            return true
        }

        try{
            val result = buildCredentialRequest()
            val tokenCredential = GoogleIdTokenCredential.createFrom(result.credential.data)
            /*val loginId = tokenCredential.id
            val profilePhoto = tokenCredential.profilePictureUri
            val name = tokenCredential.displayName*/
            return handleSignIn(result)
        }catch (e: Exception){
            e.printStackTrace()
        }
        return false
    }

    // This is for signOut
    suspend fun signOut(){
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
        firebaseAuth.signOut()
    }

    // To check if the user is signedIn
    fun isSignedIn(): Boolean{
        if(firebaseAuth.currentUser!=null){
            return true
        }
        return false
    }

    /**
     * Build a credential request
     * filterByAuthorizedAccounts = false : Shows all Google Accounts, not just the ones previously authorized
     * setAutoSelectEnabled = false: lets users choose an account rather than auto-selecting one
     * serverclientId: This is mandatory - your "web client Id" from
     *  firebase console ->authentication -> sign-in method -> Google*/
    private suspend fun buildCredentialRequest(): GetCredentialResponse{
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("284023556581-q2tv2rcv3jalppqctmodpps9emn4gf7f.apps.googleusercontent.com")
                    .setAutoSelectEnabled(false)
                    .build()
            ).build()

        return credentialManager.getCredential(request = request, context = context)
    }

    /**
     * In the above method, buildCredentialRequest() -> we communicated with Firebase Auth and received a response
     * this Credential Response needs to be handled*/
    private suspend fun handleSignIn(response: GetCredentialResponse): Boolean{
        val credential = response.credential
        if(credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ){
            try{
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val authCredential = GoogleAuthProvider.getCredential(tokenCredential.idToken, null)
                val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                return authResult.user!=null
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

        return false
    }

}