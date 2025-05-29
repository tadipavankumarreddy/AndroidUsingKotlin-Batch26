package com.nareshtech.jsontypicode_retrofit.domain.usecase

import com.nareshtech.jsontypicode_retrofit.data.repository.PostRepositoryImpl
import com.nareshtech.jsontypicode_retrofit.domain.model.Post

// For Now, directly use PostRepositoryImpl.
class GetPostsUseCase {
    private val repository = PostRepositoryImpl()

    suspend operator fun invoke(): List<Post>{
        // Map DTO to domain model
        return repository.getPosts().map { dto->
            Post(
                userId = dto.userID,
                id = dto.id,
                title = dto.title,
                body = dto.body
            )
        }
    }
}