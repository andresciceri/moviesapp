package com.example.moviesapp.di

import com.example.base.di.ActivityScope
import com.example.moviesapp.services.MoviesServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MoviesModule {

    @Provides
    @JvmStatic
    @ActivityScope
    fun MoviesServices(retrofit: Retrofit): MoviesServices = retrofit.create(MoviesServices::class.java)

}