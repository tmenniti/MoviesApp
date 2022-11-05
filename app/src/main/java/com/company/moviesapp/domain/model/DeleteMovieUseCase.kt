package com.company.moviesapp.domain.model

import com.company.moviesapp.data.MovieRepository
import com.company.moviesapp.data.database.entities.MovieEntity
import javax.inject.Inject

class DeleteMovieUseCase @Inject constructor(
    private val repository : MovieRepository
) {

    suspend operator fun invoke(movie : MovieEntity) = repository.deleteMovie(movie)

}