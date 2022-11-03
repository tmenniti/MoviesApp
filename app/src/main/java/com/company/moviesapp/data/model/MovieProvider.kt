package com.company.moviesapp.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieProvider @Inject constructor() {
    var movies : List<MovieModel> = emptyList()
}