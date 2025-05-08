package com.nareshtech.favoritemoviesjc

import android.content.Context
import android.graphics.Movie
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.favoritemoviesjc.ui.theme.FavoriteMoviesJCTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FavoriteMoviesJCTheme {
                MoviesList(context = applicationContext,movies = prepareData())
            }
        }
    }
}

// TODO 5: MoviesList
@Composable
fun MoviesList(context:Context,movies:List<FavMovies>){
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
      items(movies){
          MovieItem(context,it)
      }
    }
}

// TODO 4: Design one Item
@Composable
fun MovieItem(context:Context,movie:FavMovies){
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(movie.image),
            contentDescription = movie.movieName,
            modifier = Modifier.height(200.dp).width(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Text(text = movie.movieName,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = movie.actorName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = Color.DarkGray
        )
    }
}

@Composable
fun DetailItem(movie: FavMovies) {
    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.statusBars.asPaddingValues()).padding(WindowInsets.navigationBars.asPaddingValues()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(painter = painterResource(movie.image), contentDescription = movie.movieName)
        Text(movie.movieName)
        Text(movie.actorName)
        Text(movie.shortSynopsis)
        Text(movie.releaseYear.toString())
    }
}

// TODO 1: Place the images downloaded in the drawable Folder
// TODO 2: Prepare a Data class to structure your data
data class FavMovies(
    val image:Int,
    val movieName:String,
    val actorName:String,
    val shortSynopsis:String,
    val releaseYear:Int
)

// TODO 3: Prepare data
private fun prepareData() = mutableListOf(
    FavMovies(
        R.drawable.a,
        "Avengers",
        "Robert Downey Jr.",
        "Loki attacks Earth, stealing the Tesseract. Nick Fury assembles Iron Man, Captain America, Hulk, Thor, Black Widow, and Hawkeye to stop him and his alien army.",
        2012
    ),
    FavMovies(R.drawable.b,
        "Bahubali",
        "Prabhas",
        "A tribal man Shivudu, living near a giant waterfall, yearns to explore beyond. He discovers his royal heritage and confronts his destiny against a tyrannical ruler.",
        2015),
    FavMovies(R.drawable.c,
        "Chichhore",
        "Sushant singh Rajput",
        "A tragic incident forces Anirudh, a middle-aged man, to take a trip down memory lane and reminisce his college days along with his friends, who were labelled as losers.",
        2019),
    FavMovies(R.drawable.d,
        "Dangal",
        "Amir Khan",
        "A determined father, Mahavir Phogat, trains his daughters Geeta and Babita to become world-class wrestlers, defying societal norms.",
        2016),
    FavMovies(R.drawable.e,
        "Eega",
        "Nani",
        "A man is reborn as a housefly after being murdered, seeking revenge on his killer and protecting his love.",
        2012),
    FavMovies(R.drawable.f,
        "F2",
        "Venkatesh",
        "Two men struggle with their dominating wives, leading to humorous situations as they seek peace and freedom from marital frustrations",
        2019),
    FavMovies(R.drawable.g,
        "The Ghazi Attack",
        "Rana Daggubati",
        " An Indian submarine crew prevents a Pakistani submarine attack during the 1971 war, showcasing bravery, strategy, and patriotism underwater.",
        2017),
    FavMovies(R.drawable.h,
        "Husharu",
        "Rahul",
        "Husharu is a 2018 Telugu romantic comedy-drama film about a group of carefree friends navigating love, life, and responsibilities.",
        2018),
    FavMovies(R.drawable.i,
        "I",
        "Vikram",
        "I is a romantic thriller about a bodybuilder-turned-model who seeks revenge after being deformed by rivals who are also after his love, a supermodel",
        2014),
    FavMovies(R.drawable.j,
        "Jack",
        "Siddu",
        "The main character is struggling to understand his son, Pablo Neruda. Unaware of his son's activities, he is desperate to find out what he does for a living.",
        2025)
)