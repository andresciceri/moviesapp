package com.example.moviesapp

import android.content.Context
import com.example.base.BaseApplication
import com.example.moviesapp.di.DaggerMainComponent

class MoviesApplication : BaseApplication() {

    companion object {
        lateinit var instance: MoviesApplication
            private set

        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

    override fun initializeInjector() {
        component = DaggerMainComponent.builder()
            .application(this)
            .build()
            .apply { inject(this@MoviesApplication) }
    }
}