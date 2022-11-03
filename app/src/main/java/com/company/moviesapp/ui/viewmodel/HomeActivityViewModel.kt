package com.company.moviesapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.moviesapp.data.model.MovieModel
import com.company.moviesapp.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeActivityViewModel @Inject constructor(
    private val getMoviesUseCase : GetMoviesUseCase
) : ViewModel() {

    val mldMovies = MutableLiveData<List<MovieModel>>()

    fun getMovies() {
        viewModelScope.launch {
            val result = getMoviesUseCase()

            if (!result.isNullOrEmpty()) {
                mldMovies.postValue(result)
            }
        }
    }
}