package com.neilsayok.musewearables.application

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader


import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {


    companion object {
        lateinit var INSTANCE: MainApplication
    }

    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        initFlipper()
    }




    override fun onLowMemory() {
        super.onLowMemory()
        Log.i("Application", "onLowMemory")
    }

    private fun initFlipper() {

        SoLoader.init(this, false)
        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
        client.addPlugin(NetworkFlipperPlugin())
        client.start()

    }


}