package com.nareshtech.notetakingapp

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun GoogleSignInScreen(context: Context, viewModel: NoteViewModel = viewModel()){
    val googleAuthClient = GoogleAuthClient(context)

    var isSignIn by rememberSaveable {
        mutableStateOf(googleAuthClient.isSignedIn())
    }

    Box(modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues()).padding(
        WindowInsets.navigationBars.asPaddingValues()),
        contentAlignment = Alignment.Center){
        if(isSignIn){
            /*OutlinedButton(onClick = {
                viewModel.viewModelScope.launch {
                    googleAuthClient.signOut()
                    isSignIn = false
                }
            }) {
                Text(
                    text = "Sign Out", fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }*/
            NoteListScreen(viewModel)
        }else{
            OutlinedButton(onClick = {
                viewModel.viewModelScope.launch {
                    isSignIn = googleAuthClient.signIn()
                }
            }) {
                Text(
                    text = "Sign In with Google", fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                )
            }
        }
    }
}