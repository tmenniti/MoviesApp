package com.company.moviesapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.company.moviesapp.data.database.dao.MovieDao
import com.company.moviesapp.data.database.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMovieDao() : MovieDao

}