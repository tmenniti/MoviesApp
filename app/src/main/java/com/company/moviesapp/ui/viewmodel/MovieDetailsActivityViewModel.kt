package com.company.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.moviesapp.data.database.entities.MovieEntity
import com.company.moviesapp.domain.GetLocalMoviesUseCase
import com.company.moviesapp.domain.SaveMovieUseCase
import com.company.moviesapp.domain.model.DeleteMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsActivityViewModel @Inject constructor(
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val saveMovieUseCase : SaveMovieUseCase,
    private val deleteMovieUseCase : DeleteMovieUseCase,
) : ViewModel() {

    val mldSaveMovie = MutableLiveData<MovieEntity>()
    val mldDeleteMovie = MutableLiveData<MovieEntity>()

    fun saveMovie(movie : MovieEntity) {
        viewModelScope.launch {
            saveMovieUseCase(movie)
            mldSaveMovie.postValue(movie)
        }
    }

    fun deleteMovie(movie : MovieEntity) {
        viewModelScope.launch {
            deleteMovieUseCase(movie)
            mldDeleteMovie.postValue(movie)
        }
    }
}