package com.company.moviesapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.moviesapp.R
import com.company.moviesapp.core.IMAGES_BASE_URL
import com.company.moviesapp.core.setImageGlide
import com.company.moviesapp.databinding.ItemRowFavoritesBinding
import com.company.moviesapp.domain.model.Movie

class FavoritesAdapter(
    private val context: Context,
    private val favoritesMoviesList: List<Movie>
) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_favorites, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieBackdropPath : String = favoritesMoviesList[position].backdrop_path

        context.setImageGlide(IMAGES_BASE_URL + movieBackdropPath, holder.binding.imgMovie)
    }

    override fun getItemCount(): Int {
        return favoritesMoviesList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemRowFavoritesBinding.bind(view)
    }
}