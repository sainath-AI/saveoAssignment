package com.masai.sainath.saveoproject.data.remote

import com.masai.sainath.saveoproject.data.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    /**
     * function calling api with integer as query referring to page
     */
    @GET("shows")
    fun getMovieList(@Query("page") id: Int): Call<List<MovieModel>>
}