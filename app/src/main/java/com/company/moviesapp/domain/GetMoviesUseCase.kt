package com.company.moviesapp.domain

import com.company.moviesapp.data.MovieRepository
import com.company.moviesapp.data.model.MovieModel

class GetMoviesUseCase {

    private val repository = MovieRepository()

    suspend operator fun invoke() : List<MovieModel> = repository.getAllMovies().results

}