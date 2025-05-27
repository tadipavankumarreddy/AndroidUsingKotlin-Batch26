package com.nareshtech.notetakingapp

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.logger.Logger
import com.nareshtech.notetakingapp.room.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class FirestoreNoteRepository {
    private val db = Firebase.firestore

    private val notesCollection = db.collection("notes")

    private val note = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = note.asStateFlow()

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing.asStateFlow()

    init {
        notesCollection.addSnapshotListener { snapshot, error ->
            if(error!=null) return@addSnapshotListener
            val notes = snapshot?.toObjects(Note::class.java)
            note.value = notes ?: emptyList()
        }
    }

     suspend fun syncNoteToFirestore(notes:List<Note>) {
         _isSyncing.value = true
         val uid = Firebase.auth.currentUser?.email

         try {
             notes.forEach { note ->
                 val noteMap = mapOf(
                     "id" to note.id,
                     "title" to note.title,
                     "content" to note.content
                 )
                 db.collection(uid!!).document(note.id.toString()).set(noteMap).await()
             }

             val snapshot = db.collection(uid!!).get().await()
             val firestoreIds = snapshot.documents.mapNotNull { it.id.toIntOrNull() }
             val localIds = notes.map { it.id }
             val idsToDelete = firestoreIds - localIds.toSet()
             Log.e("Main","Ids to delete $idsToDelete")
             if(idsToDelete.size!=null){
                 db.collection(uid!!).whereIn("id",idsToDelete).get().await()
                     .documents.forEach { it.reference.delete().await() }
             }
         }catch (e: Exception){
             e.printStackTrace()
         }finally {
             _isSyncing.value = false
         }
     }
}