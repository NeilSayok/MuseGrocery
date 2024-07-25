package com.neilsayok.musewearables.application

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate


import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {


    companion object {
        lateinit var INSTANCE: Application
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }




    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("Application", "onLowMemory")
    }


}