package com.nareshtech.jsontypicode_retrofit.domain.model

data class Post(
    val userId:Int,
    val id:Int? = null,
    val title: String,
    val body: String
)