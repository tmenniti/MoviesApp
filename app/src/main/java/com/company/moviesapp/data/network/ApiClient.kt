package com.company.moviesapp.data.network

import com.company.moviesapp.data.model.GenreModel
import com.company.moviesapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("api_key") apiKey: String): Response<List<GenreModel>>

}