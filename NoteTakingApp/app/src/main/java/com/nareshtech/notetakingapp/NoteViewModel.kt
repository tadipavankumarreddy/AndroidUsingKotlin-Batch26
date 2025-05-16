package com.nareshtech.notetakingapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nareshtech.notetakingapp.room.Note
import com.nareshtech.notetakingapp.room.NoteDatabase
import com.nareshtech.notetakingapp.room.NoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// TODO 6: Create a Viewmodel
class NoteViewModel(application: Application): AndroidViewModel(application) {
    private val repo : NoteRepository

    val notes: StateFlow<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).noteDao()
        repo = NoteRepository(dao)
        notes = repo.notes.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    }

    fun addNote(note: Note) = viewModelScope.launch { repo.addNote(note) }
    fun deleteNote(note:Note) = viewModelScope.launch { repo.deleteNote(note) }
}