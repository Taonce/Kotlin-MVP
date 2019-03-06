package com.taonce.mvp

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco

class App : Application() {

    companion object {
        @JvmStatic lateinit var app: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        Fresco.initialize(app)
    }

}


