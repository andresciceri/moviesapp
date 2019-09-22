package com.example.moviesapp.di

import com.example.base.di.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        MoviesActivityBuilder::class,
        AndroidSupportInjectionModule::class
    ]
)
interface MainComponent: BaseComponent {

    @Component.Builder
    interface Builder {
        fun build(): MainComponent

        @BindsInstance
        fun application(application: InjectableApplication): Builder
    }
}