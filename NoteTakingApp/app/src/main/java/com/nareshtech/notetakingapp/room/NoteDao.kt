package com.nareshtech.notetakingapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// TODO 3: Create a Data access Object interface for listing out all the queries you want to perform on Room DB.

@Dao
interface NoteDao {

    @Query("select * from notes")
    fun getAllNotes(): Flow<List<Note>>

    @Insert
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note:Note)
}