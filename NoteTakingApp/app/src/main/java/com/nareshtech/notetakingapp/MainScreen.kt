package com.nareshtech.notetakingapp

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(context: Context, viewModel: NoteViewModel = viewModel()) {
    var isSignedIn by rememberSaveable { mutableStateOf(GoogleAuthClient(context).isSignedIn()) }
    val googleAuthClient = remember { GoogleAuthClient(context) }

    LaunchedEffect(Unit) {
        isSignedIn = googleAuthClient.isSignedIn()
    }

    if (isSignedIn) {
        NoteListScreen(
            viewModel = viewModel,
            onSignOut = {
                viewModel.viewModelScope.launch {
                    googleAuthClient.signOut()
                    isSignedIn = false
                }
            }
        )
    } else {
        GoogleSignInScreen(
            context = context,
            onSignInSuccess = {
                isSignedIn = true
            }
        )
    }
}
