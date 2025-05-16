package com.nareshtech.notetakingapp.room

// TODO 5: Creating a repository which can be access for the tasks that we want to do with data (network/Room/assets)

class NoteRepository(private val dao: NoteDao) {
    val notes = dao.getAllNotes()
    suspend fun addNote(note: Note) = dao.insertNote(note)
    suspend fun deleteNote(note: Note) = dao.deleteNote(note)
}