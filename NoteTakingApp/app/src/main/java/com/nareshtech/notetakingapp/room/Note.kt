package com.nareshtech.notetakingapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

// TODO 2: Create a Note Entity - This becomes the table

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String = "",
    val content:String = ""
)
