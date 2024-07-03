package com.devjeem.tasklist.di

import android.app.Application
import android.util.Log
import com.devjeem.tasklist.ui.util.SharedPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltFirstApp : Application() {
    var prefereces: SharedPreferences? = null

    override fun onCreate() {
        super.onCreate()
        Log.d("HiltFirstApp", "onCreate: Success")
        prefereces = SharedPreferences(applicationContext)
    }

}