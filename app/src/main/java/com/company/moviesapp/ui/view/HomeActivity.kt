package com.company.moviesapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.moviesapp.R
import com.company.moviesapp.core.isOnline
import com.company.moviesapp.core.showToast
import com.company.moviesapp.data.model.GenreModel
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.databinding.ActivityHomeBinding
import com.company.moviesapp.domain.model.Movie
import com.company.moviesapp.ui.adapters.FavoritesAdapter
import com.company.moviesapp.ui.adapters.MovieAdapter
import com.company.moviesapp.ui.interfaces.MovieInterface
import com.company.moviesapp.ui.viewmodel.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MovieInterface {

    private var favoritesMoviesList : List<Movie> = emptyList()
    private var moviesList : List<MovieModel> = emptyList()
    private var genresList : List<GenreModel> = emptyList()

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
        getMovies()
    }

    private fun setObservers() {
        viewModel.mldLocalMovies.observe(this) {
            favoritesMoviesList = it

            if (favoritesMoviesList.isNotEmpty()) {
                showFavorites()
                setFavoritesRecyclerView()
            } else {
                hideFavorites()
            }
        }

        viewModel.mldMovies.observe(this) {
            moviesList = it
            getGenres()
        }

        viewModel.mldGenres.observe(this) {
            genresList = it
            setMoviesRecyclerView()
        }
    }

    private fun getMovies() {
        if (isOnline()) {
            viewModel.getMovies()
        } else {
            showToast(resources.getString(R.string.connection_error))
        }
    }

    private fun getGenres() {
        if (isOnline()) {
            viewModel.getGenres()
        } else {
            showToast(resources.getString(R.string.connection_error))
        }
    }

    private fun showFavorites() {
        binding.tvFavorites.visibility = View.VISIBLE
        binding.rvFavorites.visibility = View.VISIBLE
    }

    private fun setFavoritesRecyclerView() {
        val favoritesAdapter = FavoritesAdapter(this, favoritesMoviesList, this)
        binding.rvFavorites.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvFavorites.adapter = favoritesAdapter
    }

    private fun hideFavorites() {
        binding.tvFavorites.visibility = View.GONE
        binding.rvFavorites.visibility = View.GONE
    }

    private fun setMoviesRecyclerView() {
        val movieAdapter = MovieAdapter(this, moviesList, genresList, this)
        binding.rvMovies.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding.rvMovies.adapter = movieAdapter
    }

    private fun getFavoritesIdsList() : ArrayList<Int> {
        var favoritesIdsList = arrayListOf<Int>()

        for (i in favoritesMoviesList.indices) {
            favoritesIdsList.add(favoritesMoviesList[i].id)
        }

        return favoritesIdsList
    }

    private fun seeMovieDetails(movie: MovieModel) {
        val intent = Intent(this, MovieDetailsActivity::class.java)

        intent.putIntegerArrayListExtra("favoritesIdsList", getFavoritesIdsList())
        intent.putExtra("movie", movie)

        startActivity(intent)
    }

    override fun onMovieClicked(movie: MovieModel) {
        seeMovieDetails(movie)
    }

    override fun onFavoriteClicked(movie: MovieModel) {
        seeMovieDetails(movie)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocalMovies()
    }
}