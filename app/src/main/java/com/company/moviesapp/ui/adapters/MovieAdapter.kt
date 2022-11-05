package com.company.moviesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.moviesapp.R
import com.company.moviesapp.core.IMAGES_BASE_URL
import com.company.moviesapp.core.setImageGlide
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.databinding.ItemRowMoviesBinding
import com.company.moviesapp.ui.interfaces.MovieInterface

class MovieAdapter(
    private val context: Context,
    private val moviesList: List<MovieModel>,
    private val movieInterface: MovieInterface
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_movies, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieTitle : String = moviesList[position].title
        val movieGenre : Int = moviesList[position].genre_ids[0]
        val movieBackdropPath : String = moviesList[position].backdrop_path

        holder.binding.tvMovie.text = movieTitle
        holder.binding.tvGenre.text = movieGenre.toString()
        context.setImageGlide(IMAGES_BASE_URL + movieBackdropPath, holder.binding.imgMovie)

        holder.binding.cvMovie.setOnClickListener {
            movieInterface.onMovieClicked(moviesList[position])
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRowMoviesBinding.bind(view)
    }
}