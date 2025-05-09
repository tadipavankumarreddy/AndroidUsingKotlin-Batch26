package com.nareshtech.favoritemoviesrv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FavMoviesAdapter(val context:Context,val moviesList: List<FavMovies>)
    : RecyclerView.Adapter<FavMoviesAdapter.FavMoviesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavMoviesViewHolder {
      val v = LayoutInflater.from(context).inflate(R.layout.one_item_design,parent,false)
      return FavMoviesViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: FavMoviesViewHolder,
        position: Int
    ) {
        holder.iv.setImageResource(moviesList[position].image)
        holder.movieName.text = moviesList[position].movieName
        holder.actorName.text = moviesList[position].actorName
        holder.itemView.setOnClickListener {
            Toast.makeText(context,moviesList[position].movieName,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class FavMoviesViewHolder(val itemView: View): RecyclerView.ViewHolder(itemView) {
        val iv = itemView.findViewById<ImageView>(R.id.imageView)
        val movieName = itemView.findViewById<TextView>(R.id.movie_name)
        val actorName = itemView.findViewById<TextView>(R.id.actor_name)
    }
}