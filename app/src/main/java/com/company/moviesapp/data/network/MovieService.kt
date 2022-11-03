package com.company.moviesapp.data.network

import com.company.moviesapp.core.API_KEY
import com.company.moviesapp.core.RetrofitSingleton
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

}