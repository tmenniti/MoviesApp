package com.company.moviesapp.data.database.dao

import androidx.room.*
import com.company.moviesapp.data.database.entities.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies_table ORDER BY id ASC")
    suspend fun getMovies() : List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}