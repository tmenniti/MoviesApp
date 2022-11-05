package com.company.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.moviesapp.data.model.GenreModel
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.domain.GetGenresUseCase
import com.company.moviesapp.domain.GetLocalMoviesUseCase
import com.company.moviesapp.domain.GetMoviesUseCase
import com.company.moviesapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val getLocalMoviesUseCase: GetLocalMoviesUseCase,
    private val getMoviesUseCase : GetMoviesUseCase,
    private val getGenresUseCase : GetGenresUseCase
) : ViewModel() {

    val mldLocalMovies = MutableLiveData<List<Movie>>()
    val mldMovies = MutableLiveData<List<MovieModel>>()
    val mldGenres = MutableLiveData<List<GenreModel>>()

    fun getLocalMovies() {
        viewModelScope.launch {
            val result = getLocalMoviesUseCase()

            mldLocalMovies.postValue(result)
        }
    }

    fun getMovies() {
        viewModelScope.launch {
            val result = getMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                mldMovies.postValue(result)
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            val result = getGenresUseCase()

            if (!result.isNullOrEmpty()) {
                mldGenres.postValue(result)
            }
        }
    }
}