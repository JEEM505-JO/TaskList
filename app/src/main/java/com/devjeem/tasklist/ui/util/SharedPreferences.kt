package com.devjeem.tasklist.ui.util

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(context: Context) {
    private val API = "API_KEY"
    private val SHARED_NAME = "SHARED"

    val shared: SharedPreferences = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveLogin(sesion: String) {
        shared.edit()?.putString(API, sesion)?.apply()
    }

    fun getLogin(): String? {
        return shared.getString(API, "")
    }

}