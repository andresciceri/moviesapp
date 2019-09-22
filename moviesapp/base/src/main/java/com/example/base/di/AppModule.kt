package com.example.base.di

import android.content.Context
import com.example.base.persistence.MoviesDao
import com.example.base.persistence.MoviesDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.example.base.utils.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun providesGson() = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()

    @Provides
    @Singleton
    fun providesResources(application: InjectableApplication) = application.resources

    @Provides
    @Singleton
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()

    @Provides
    @Singleton
    fun provideMoviesDataSource(application: InjectableApplication): MoviesDao = MoviesDatabase.getInstance(application.applicationContext).moviesDao()
}