package com.nareshtech.jsontypicode_retrofit.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nareshtech.jsontypicode_retrofit.domain.model.Post
import com.nareshtech.jsontypicode_retrofit.domain.usecase.CreatePostUseCase
import com.nareshtech.jsontypicode_retrofit.domain.usecase.GetPostsUseCase
import com.nareshtech.jsontypicode_retrofit.presentation.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    private val getPostsUseCase = GetPostsUseCase()
    private val createPostUseCase = CreatePostUseCase()

    private val _postsState = MutableStateFlow<Resource<List<Post>>>(Resource.Loading())
    val postsState: StateFlow<Resource<List<Post>>> = _postsState.asStateFlow()

    private val _createPostState = MutableStateFlow<Resource<Post>>(Resource.Loading())
    val createPostState: StateFlow<Resource<Post>> = _createPostState.asStateFlow()

    init {

    }

    fun getPosts(){
        viewModelScope.launch {
            _postsState.value = Resource.Loading()
            try{
                val posts =getPostsUseCase()
                _postsState.value = Resource.Success(posts)
            }catch (e: Exception){
                _postsState.value = Resource.Error("Error Fetching Posts: ${e.localizedMessage}")
            }
        }
    }

    fun createPost(title:String, body:String, userId:Int){
        viewModelScope.launch {
            _createPostState.value = Resource.Loading()
            try {
                val newPost = Post(title = title, body = body, userId = userId)
                val createdPost = createPostUseCase(newPost)
                _createPostState.value = Resource.Success(createdPost)
            }catch (e: Exception){
                _createPostState.value = Resource.Error("Error Fetching Posts: ${e.localizedMessage}")
            }
        }
    }

    // Reset create post state after showing error/success
    fun resetCreatePostState(){
        _createPostState.value = Resource.Loading(null)
    }

}