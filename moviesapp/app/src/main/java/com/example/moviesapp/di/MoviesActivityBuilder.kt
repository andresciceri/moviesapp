package com.example.moviesapp.di

import com.example.base.di.ActivityScope
import com.example.moviesapp.activities.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityBuilder {
    @ContributesAndroidInjector(modules = [MoviesModule::class])
    @ActivityScope
    abstract fun bindMoviesActivity(): HomeActivity
}