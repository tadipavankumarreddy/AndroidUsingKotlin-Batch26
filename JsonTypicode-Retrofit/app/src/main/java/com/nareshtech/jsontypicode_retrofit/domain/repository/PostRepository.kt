package com.nareshtech.jsontypicode_retrofit.domain.repository

import com.nareshtech.jsontypicode_retrofit.domain.model.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun createPost(post: Post): Post
}