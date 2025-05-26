package com.nareshtech.notetakingapp

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObjects
import com.nareshtech.notetakingapp.room.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirestoreNoteRepository {
    private val db = Firebase.firestore

    private val notesCollection = db.collection("notes")

    private val note = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = note.asStateFlow()

    init {
        notesCollection.addSnapshotListener { snapshot, error ->
            if(error!=null) return@addSnapshotListener
            val notes = snapshot?.toObjects(Note::class.java)
            note.value = notes ?: emptyList()
        }
    }

    suspend fun syncNoteToFirestore(notes:List<Note>){
        val uid = Firebase.auth.currentUser?.email
        notes.forEach { note->
            val noteMap = mapOf(
                "id" to note.id,
                "title" to note.title,
                "content" to note.content
            )

            db.collection(uid!!).document(note.id.toString())
                .set(noteMap)
        }
    }
}