package com.example.moviesapp.controllers

import com.example.base.models.MoviesResponse
import com.example.moviesapp.repositories.MoviesRepository
import io.reactivex.Single
import javax.inject.Inject

class MoviesController @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun getMovies(apiKey: String): Single<MoviesResponse> {
        return moviesRepository.getMovies(apiKey)
    }

    fun getMovies(apiKey: String, filter: String): Single<MoviesResponse> {
        return moviesRepository.getMovies(apiKey, filter)
    }
}
