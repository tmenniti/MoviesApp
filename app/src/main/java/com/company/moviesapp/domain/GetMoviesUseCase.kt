package com.company.moviesapp.domain

import com.company.moviesapp.data.MovieRepository
import com.company.moviesapp.data.model.MovieModel
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository : MovieRepository
) {

    suspend operator fun invoke() : List<MovieModel> = repository.getAllMovies().results

}