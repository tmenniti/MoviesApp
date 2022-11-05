package com.company.moviesapp.data.model

data class GenreResponse(
    val genres: List<GenreModel>
)

data class GenreModel(
    val id:Int,
    val name: String,
)