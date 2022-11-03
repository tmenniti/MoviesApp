package com.company.moviesapp.data

import com.company.moviesapp.data.model.MovieProvider
import com.company.moviesapp.data.model.MovieResponse
import com.company.moviesapp.data.network.MovieService

class MovieRepository {

    private val api = MovieService()

    suspend fun getAllMovies(): MovieResponse {
        val response = api.getMovies()
        MovieProvider.movies = response.results
        return response
    }

}