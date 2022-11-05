package com.company.moviesapp.data.network

import com.company.moviesapp.core.API_KEY
import com.company.moviesapp.data.model.GenreModel
import com.company.moviesapp.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieService @Inject constructor(private val api : ApiClient) {

    suspend fun getMovies() : MovieResponse {
        return withContext(Dispatchers.IO) {
            val response = api.getTopRatedMovies(API_KEY, 1)
            response
        }
    }

    suspend fun getGenres() : List<GenreModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getGenres(API_KEY)
            response.genres
        }
    }

}