package com.example.moviesapp.services

import com.example.base.models.MoviesResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesServices {

    @GET("?format=json")
    fun getMovies(@Query("api_key") apiKey: String): Single<MoviesResponse>

    @GET("?format=json")
    fun getMoviesFilters(@Query("filter", encoded = true) filter: String,
                         @Query("api_key") apiKey: String): Single<MoviesResponse>
}
