package com.nareshtech.jsontypicode_retrofit.domain.usecase

import com.nareshtech.jsontypicode_retrofit.data.model.PostDto
import com.nareshtech.jsontypicode_retrofit.data.repository.PostRepositoryImpl
import com.nareshtech.jsontypicode_retrofit.domain.model.Post

class CreatePostUseCase {
    private val repository = PostRepositoryImpl()

    suspend operator fun invoke(post:Post): Post{
        val postDto = PostDto(
            userID = post.userId,
            title = post.title,
            body = post.body
        )

        val createdDto = repository.createPost(postDto)

        return Post(
            userId = createdDto.userID,
            id = createdDto.id,
            title = createdDto.title,
            body = createdDto.body
        )
    }
}