package com.company.moviesapp.ui.interfaces

import com.company.moviesapp.data.model.MovieModel

interface MovieInterface {

    fun onMovieClicked(movie : MovieModel)
    fun onFavoriteClicked(movie : MovieModel)
}