package com.example.base

import com.example.base.di.BaseComponent
import com.example.base.di.InjectableApplication

abstract class BaseApplication : InjectableApplication() {

    var component: BaseComponent? = null
        protected set

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}