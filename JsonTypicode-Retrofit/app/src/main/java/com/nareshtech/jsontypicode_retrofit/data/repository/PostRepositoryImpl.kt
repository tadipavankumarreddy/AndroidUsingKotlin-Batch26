package com.nareshtech.jsontypicode_retrofit.data.repository

import com.google.gson.GsonBuilder
import com.nareshtech.jsontypicode_retrofit.data.PostDto
import com.nareshtech.jsontypicode_retrofit.data.remote.JsonPlaceholderAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: JsonPlaceholderAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderAPI::class.java)
    }
}

// Implements the abstract domain repository
class PostRepositoryImpl{

    private val api = RetrofitClient.api

    suspend fun getPosts(): List<PostDto>{
        val response = api.getPosts()
        if(response.isSuccessful && response.body()!=null){
            return response.body()!!
        }else{
            throw Exception("Failed to fetch posts:${response.code()}")
        }
    }

    suspend fun createPost(post: PostDto): PostDto{
        val response = api.createPost(post)

        if(response.isSuccessful && response.body()!=null){
            return response.body()!!
        }else{
            throw Exception("Failed to Create posts:${response.code()}")
        }
    }
}

