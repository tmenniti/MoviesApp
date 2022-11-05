package com.company.moviesapp.domain

import com.company.moviesapp.data.MovieRepository
import com.company.moviesapp.data.database.entities.MovieEntity
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.domain.model.Movie
import javax.inject.Inject

class SaveMovieUseCase @Inject constructor(
    private val repository : MovieRepository
) {

    suspend operator fun invoke(movie : MovieEntity) = repository.saveMovie(movie)

}