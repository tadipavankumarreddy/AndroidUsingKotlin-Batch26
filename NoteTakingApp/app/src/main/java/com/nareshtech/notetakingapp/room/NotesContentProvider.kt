package com.nareshtech.notetakingapp.room

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.room.Room

class NotesContentProvider : ContentProvider() {

    private lateinit var notesDao: NoteDao

    companion object{
        const val AUTHORITY = "com.nareshtech.notetakingapp.CONTENT_PROVIDER"
        val NOTES_URI: Uri = Uri.parse("content://$AUTHORITY")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
       return 0
    }

    override fun getType(uri: Uri): String? {
        return ""
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
       return uri
    }

    override fun onCreate(): Boolean {
        val db = Room.databaseBuilder(
            context!!,
            NoteDatabase::class.java,
            "note_db"
        ).build()

        notesDao = db.noteDao()
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val cursor = notesDao.getNotesCursor()
        cursor.setNotificationUri(context!!.contentResolver, NOTES_URI)
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}