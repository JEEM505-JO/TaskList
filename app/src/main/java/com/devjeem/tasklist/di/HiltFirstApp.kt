package com.devjeem.tasklist.di

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltFirstApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("HiltFirstApp", "onCreate: Success")
    }

}