package com.company.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.domain.GetMoviesUseCase
import kotlinx.coroutines.launch

class HomeActivityViewModel : ViewModel() {

    val mldMovies = MutableLiveData<List<MovieModel>>()

    var getMoviesUseCase = GetMoviesUseCase()

    fun getMovies() {
        viewModelScope.launch {
            val result = getMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                mldMovies.postValue(result)
            }
        }
    }
}