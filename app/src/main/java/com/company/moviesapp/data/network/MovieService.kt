package com.company.moviesapp.data.network

import com.company.moviesapp.core.API_KEY
import com.company.moviesapp.core.RetrofitSingleton
import com.company.moviesapp.data.model.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieService {

    private val retrofit = RetrofitSingleton.getRetrofit()

    suspend fun getMovies() : MovieResponse {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getTopRatedMovies(API_KEY, 1)
            response
        }
    }

}