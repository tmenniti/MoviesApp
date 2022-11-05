package com.company.moviesapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.company.moviesapp.R
import com.company.moviesapp.core.IMAGES_BASE_URL
import com.company.moviesapp.core.setImageGlide
import com.company.moviesapp.core.showToast
import com.company.moviesapp.data.database.entities.MovieEntity
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.databinding.ActivityMovieDetailsBinding
import com.company.moviesapp.ui.viewmodel.MovieDetailsActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private var favoritesIdsList: ArrayList<Int> = arrayListOf()
    private lateinit var movie: MovieModel

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        getMovies()
        setViewsInformation()

        binding.btFavorite.setOnClickListener {
            if (isFavorite()) {
                viewModel.deleteMovie(getMovieEntity())
            } else {
                viewModel.saveMovie(getMovieEntity())
            }
        }
    }

    private fun setObservers() {
        viewModel.mldSaveMovie.observe(this) {
            showToast(resources.getString(R.string.movie_added))
            favoritesIdsList.add(it.id)
            setDeleteButton()
        }

        viewModel.mldDeleteMovie.observe(this) {
            showToast(resources.getString(R.string.movie_deleted))
            favoritesIdsList.remove(it.id)
            setAddButton()
        }
    }

    private fun getMovies() {
        movie = intent.getSerializableExtra("movie") as MovieModel
        favoritesIdsList = intent.getIntegerArrayListExtra("favoritesIdsList") as ArrayList<Int>
    }

    private fun setViewsInformation() {
        setImageGlide(IMAGES_BASE_URL + movie.backdrop_path, binding.imgMovie)
        binding.tvMovie.text = movie.title
        binding.tvReleaseDate.text = movie.release_date
        binding.tvDescription.text = movie.overview

        if (isFavorite()) {
            setDeleteButton()
        } else {
            setAddButton()
        }
    }

    private fun setDeleteButton() {
        binding.btFavorite.text = resources.getString(R.string.delete_from_favorites)
        binding.btFavorite.background =
            ContextCompat.getDrawable(
                this,
                R.drawable.red_bordered_rectangle_background
            )
    }

    private fun setAddButton() {
        binding.btFavorite.text = resources.getString(R.string.add_to_favorites)
        binding.btFavorite.background =
            ContextCompat.getDrawable(
                this,
                R.drawable.blue_bordered_rectangle_background
            )
    }

    private fun isFavorite(): Boolean {
        for (i in favoritesIdsList.indices) {
            if (favoritesIdsList[i] == movie.id) {
                return true
            }
        }
        return false
    }

    private fun getMovieEntity(): MovieEntity {
        return MovieEntity(
            movie.id,
            movie.adult,
            movie.backdrop_path,
            movie.original_title,
            movie.original_title,
            movie.overview,
            movie.popularity,
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.vote_average,
            movie.vote_count
        )
    }
}