package com.nareshtech.notetakingapp

import android.widget.Toast
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nareshtech.notetakingapp.room.Note

@Composable
fun NoteListScreen(viewModel: NoteViewModel = viewModel()){

    // Collect StateFlow as Compose State
    val notes by viewModel.notes.collectAsState()

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())) {
        OutlinedTextField(value = title, onValueChange = {title = it}, label = { Text("Title") },
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(value = content, onValueChange = {content = it}, label = {Text("Content")},
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            if(title.isNotBlank() && content.isNotBlank()){
                viewModel.addNote(Note(title = title, content = content))
                content = ""
                title = ""
            }
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Add Note")
        }

        Spacer(Modifier.height(8.dp))

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