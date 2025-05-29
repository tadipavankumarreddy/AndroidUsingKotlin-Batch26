package com.nareshtech.jsontypicode_retrofit.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.nareshtech.jsontypicode_retrofit.presentation.util.Resource
import com.nareshtech.jsontypicode_retrofit.presentation.viewmodel.PostViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel = viewModel()){

    val postState by viewModel.postsState.collectAsState()
    val createPostState by viewModel.createPostState.collectAsState()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    LaunchedEffect(createPostState) {
        when(createPostState){
            is Resource.Success -> {
                val post = (createPostState as Resource.Success).data
                Toast.makeText(context, "Post Created: ID ${post?.id}", Toast.LENGTH_LONG).show()
                viewModel.resetCreatePostState()
            }

            is Resource.Error -> {
                val errorMessage = (createPostState as Resource.Error).data
                Toast.makeText(context, "Error Creating post $errorMessage", Toast.LENGTH_LONG).show()
                viewModel.resetCreatePostState()
            }

            is Resource.Loading -> {
                // Do Nothing
            }
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text("Retrofit") })
    }) { paddingValues->

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { viewModel.getPosts() },
                modifier = Modifier.fillMaxWidth()) {
                Text("GET POSTS")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // This logic is used to fetch data / error data and show it to the users
            when(postState){
                is Resource.Loading -> CircularProgressIndicator()
                is Resource.Success -> {
                    val posts = (postState as Resource.Success).data
                    if(!posts.isNullOrEmpty()){
                        Text("Fetched Posts:", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black)
                        Spacer(modifier = Modifier.height(8.dp))
                        posts.forEach { post ->
                            Text(
                                text = "ID: ${post.id}, Title: ${post.title}",
                                modifier = Modifier.fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .background(Color.LightGray.copy(alpha = 0.2f))
                                    .padding(8.dp))
                        }
                    }else{
                        Text("No Posts Fetched yet")
                    }
                }
                is Resource.Error -> {
                    val errorMessage = (postState as Resource.Error).data
                    Text("Error: $errorMessage", color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            // POST Section

            Text("Create New Post", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Black)
            Spacer(modifier = Modifier.height(16.dp))

            var title by remember { mutableStateOf("") }
            var body by remember { mutableStateOf("") }
            var userId by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                onValueChange = {title = it},
                label = {Text("Title")},
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = body,
                onValueChange = {body = it},
                label = {Text("Body")},
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = userId,
                onValueChange = {userId = it},
                label = {Text("User Id")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if(title.isNotEmpty() && body.isNotEmpty() && userId.isNotEmpty()){
                    val userIdInt = userId.toIntOrNull()
                    if(userIdInt != null){
                        viewModel.createPost(title = title, body = body, userId = userIdInt)
                    }else{
                        Toast.makeText(context, "Please enter a valid user id", Toast.LENGTH_LONG).show()
                    }
            }},
                modifier = Modifier.fillMaxWidth()) {
                Text("Create Post")
            }

            Spacer(modifier = Modifier.height(16.dp))

            when(createPostState){
                is Resource.Loading -> if(createPostState.data == null) Text("Creating Post...", color = Color.Gray)
                is Resource.Success -> {
                    val post = (createPostState as Resource.Success).data
                    if(post!=null){
                        Text("Created Post (ID: ${post.id}", modifier = Modifier.fillMaxWidth())
                    }
                }
                is Resource.Error -> {
                    val errorMessage = (createPostState as Resource.Error).data
                    Text("$errorMessage", color = Color.Red)
                }
            }
        }
    }

}