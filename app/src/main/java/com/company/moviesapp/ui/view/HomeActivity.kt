package com.company.moviesapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.databinding.ActivityHomeBinding
import com.company.moviesapp.ui.adapters.MovieAdapter
import com.company.moviesapp.ui.viewmodel.HomeActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var moviesList : List<MovieModel> = emptyList()

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
        viewModel.mldMovies.observe(this) {
            moviesList = it
            setRecyclerView()
        }
    }

    private fun getMovies() {
        viewModel.getMovies()
    }

    private fun setRecyclerView() {
        val movieAdapter = MovieAdapter(this, moviesList)
        binding.rvMovies.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        binding.rvMovies.adapter = movieAdapter
    }
}