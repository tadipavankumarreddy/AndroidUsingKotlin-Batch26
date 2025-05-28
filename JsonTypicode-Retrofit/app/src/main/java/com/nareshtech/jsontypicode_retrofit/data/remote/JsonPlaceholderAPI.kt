package com.nareshtech.jsontypicode_retrofit.data.remote

import com.nareshtech.jsontypicode_retrofit.data.PostDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface JsonPlaceholderAPI {

    @GET("posts")
    suspend fun getPosts(): Response<List<PostDto>>

    @POST("posts")
    suspend fun createPost(@Body postDto: PostDto): Response<PostDto>
}