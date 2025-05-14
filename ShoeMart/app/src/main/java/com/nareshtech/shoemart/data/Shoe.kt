package com.nareshtech.shoemart.data

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

// Todo 2: We are going to use this data class for deserializing json objects to data classes.
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class Shoe(
    val id: Int,
    val name:String,
    val brand:String,
    val type: String,
    val price:String,
    val sizes: List<String>,
    val img: String
)