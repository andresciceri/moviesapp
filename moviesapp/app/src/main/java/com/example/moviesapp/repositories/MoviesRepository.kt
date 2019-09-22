package com.example.moviesapp.repositories

import com.example.base.models.MoviesResponse
import com.example.moviesapp.services.MoviesServices
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val services: MoviesServices) {

    fun getMovies(apiKey: String): Single<MoviesResponse> {
        return services.getMovies(apiKey)
    }

    fun getMovies(apiKey: String, filter: String): Single<MoviesResponse> {
        return services.getMoviesFilters(filter, apiKey)
    }
}
