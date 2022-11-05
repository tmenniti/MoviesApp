package com.company.moviesapp.domain

import com.company.moviesapp.data.MovieRepository
import com.company.moviesapp.data.model.GenreModel
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository : MovieRepository
) {

    suspend operator fun invoke() : List<GenreModel> = repository.getGenres()

}