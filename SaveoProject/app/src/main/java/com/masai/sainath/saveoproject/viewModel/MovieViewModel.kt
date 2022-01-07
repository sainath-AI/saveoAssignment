package com.masai.sainath.saveoproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {



    fun getMovieItemList(): MutableLiveData<List<MovieModel>>  {
        return movieRepository.getMovieItemList()
    }

    fun getMovieItemListForPagination(pageNumber: Int): MutableLiveData<List<MovieModel>> {
        return movieRepository.getMovieItemListForPagination(pageNumber)
    }

}