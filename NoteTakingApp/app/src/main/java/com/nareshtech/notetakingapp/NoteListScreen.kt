package com.nareshtech.notetakingapp

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.nareshtech.notetakingapp.room.Note
import kotlinx.coroutines.launch

@Composable
fun NoteListScreen(viewModel: NoteViewModel = viewModel(), onSignOut:()-> Unit){

    // Collect StateFlow as Compose State
    val notes by viewModel.notes.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    val context = LocalContext.current

    var googleAuthClient = GoogleAuthClient(context)
    // Permission LAuncher to request Permissions from the user to post notifications.
    val permissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission(),
        onResult = {isGranted ->
            if(!isGranted){
                Toast.makeText(context,"Notification Permission denied", Toast.LENGTH_LONG).show()
            }
        })

    LaunchedEffect(Unit) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())) {


        Row(modifier = Modifier.fillMaxWidth()) {

            OutlinedButton(onClick = {
                viewModel.viewModelScope.launch {
                    onSignOut()
                }
            }) {
                Text("Sign Out")
            }
        }


        OutlinedTextField(value = title, onValueChange = {title = it}, label = { Text("Title") },
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = content, onValueChange = {content = it}, label = {Text("Content")},
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if(title.isNotBlank() && content.isNotBlank()){
                viewModel.addNote(Note(title = title, content = content))
                createNotificationChannel(context)
                showNoteAddedNotification(context,title)
                content = ""
                title = ""
            }
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Add Note")
        }

        Spacer(Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End) {
            Button(onClick = {
                // This is to send data to firestore database
                viewModel.syncNotesToFirestore(notes)
            }) {
                Icon(painter = painterResource(R.drawable.outline_cloud_sync_24), contentDescription = "Sync")
            }
        }


        LazyColumn {
            items(notes) { note ->
                Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {

                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                        Text(text = note.content, style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.height(8.dp))
                        Button(onClick = {
                            viewModel.deleteNote(note)
                        }, modifier = Modifier.fillMaxWidth()) {
                            Text("Completed")
                        }
                    }
                }
            }
        }
    }
}