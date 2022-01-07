package com.masai.sainath.saveoproject.repository

import androidx.lifecycle.MutableLiveData
import com.masai.sainath.saveoproject.data.model.MovieModel
import com.masai.sainath.saveoproject.data.remote.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: APIService) {

    /**
     * mutable live data which is being observed for setting the initial api call for page 1
     */
    val data: MutableLiveData<List<MovieModel>> = MutableLiveData()

    /**
     * mutable live data which is being observed for setting the pagination for all the rest of the pages
     */
    val pagingData: MutableLiveData<List<MovieModel>> = MutableLiveData()

    /**
     * function calling api and setting the value in livedata that is being observed by the activity
     */
    fun getMovieItemList(): MutableLiveData<List<MovieModel>> {
        apiService.getMovieList(1).enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                data.value = response.body()!!
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {

            }

        })
        return data
    }

    /**
     * function calling api and setting the value in livedata that is being observed by the activity
     * for implementing pagination
     */
    fun getMovieItemListForPagination(pageNumber: Int): MutableLiveData<List<MovieModel>> {
        apiService.getMovieList(pageNumber).enqueue(object : Callback<List<MovieModel>> {
            override fun onResponse(
                call: Call<List<MovieModel>>,
                response: Response<List<MovieModel>>
            ) {
                pagingData.value = response.body()!!
            }

            override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {

            }

        })
        return pagingData
    }
}