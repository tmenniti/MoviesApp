package com.company.moviesapp.data

import com.company.moviesapp.data.model.MovieProvider
import com.company.moviesapp.data.model.MovieResponse
import com.company.moviesapp.data.network.MovieService
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api : MovieService,
    private val movieProvider : MovieProvider
) {

    suspend fun getAllMovies(): MovieResponse {
        val response = api.getMovies()
        movieProvider.movies = response.results
        return response
    }

}