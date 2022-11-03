package com.company.moviesapp.data.model

data class MovieResponse(
    val page: Int,
    val results: List<MovieModel>
)

data class MovieModel(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_title: String,
    val original_language: String,
    val genre_ids: List<Int>,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)
