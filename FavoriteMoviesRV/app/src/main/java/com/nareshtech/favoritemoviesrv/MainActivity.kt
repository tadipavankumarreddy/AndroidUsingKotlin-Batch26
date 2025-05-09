package com.nareshtech.favoritemoviesrv

import android.os.Bundle
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // todo 3: Create a template for each item on the recyclerview (res-> layout-> one_item_design.xml)
        // todo 4: Create a RecyclerView.Adapter to bridge the gap between data and RecyclerView (we shall
        //  also use RecyclerView.ViewHolder for View Information.
        recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = FavMoviesAdapter(applicationContext,prepareData())
        /*recyclerView.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL, false)*/
        /*recyclerView.layoutManager = GridLayoutManager(applicationContext,2)*/
        recyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
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

// TODO 4: Design one Item (so that the same design gets applicable for other items)