package com.company.moviesapp.domain.model

import com.company.moviesapp.data.database.entities.MovieEntity
import com.company.moviesapp.data.model.MovieModel

data class Movie(
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_title: String,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

fun MovieEntity.toDomain() = Movie(
    id,
    adult,
    backdrop_path,
    original_title,
    original_language,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    vote_average,
    vote_count
)

fun MovieModel.toDomain() = MovieEntity(
    id,
    adult,
    backdrop_path,
    original_title,
    original_language,
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    vote_average,
    vote_count
)

fun Movie.toDomain() = MovieModel(
    id,
    adult,
    backdrop_path,
    original_title,
    original_language,
    arrayListOf(),
    overview,
    popularity,
    poster_path,
    release_date,
    title,
    vote_average,
    vote_count
)
