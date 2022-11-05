package com.company.moviesapp.data

import com.company.moviesapp.data.database.dao.MovieDao
import com.company.moviesapp.data.database.entities.MovieEntity
import com.company.moviesapp.data.model.MovieProvider
import com.company.moviesapp.data.model.MovieResponse
import com.company.moviesapp.data.network.MovieService
import com.company.moviesapp.domain.model.Movie
import com.company.moviesapp.domain.model.toDomain
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api : MovieService,
    private val movieProvider : MovieProvider,
    private val movieDao: MovieDao
) {

    suspend fun getMovies(): MovieResponse {
        val response = api.getMovies()
        movieProvider.movies = response.results
        return response
    }

    suspend fun getLocalMovies(): List<Movie> {
        val response: List<MovieEntity> = movieDao.getMovies()
        return response.map { it.toDomain() }
    }

    suspend fun saveMovie(movie : MovieEntity) {
        movieDao.saveMovie(movie)
    }

    suspend fun deleteMovie(movie : MovieEntity) {
        movieDao.deleteMovie(movie)
    }

}