package com.example.room_trial.data.db

import android.content.Context

class CacheHelper (val context: Context){

    fun getSharedPref(key:String) {
        val sharedPref = context.applicationContext?.getSharedPreferences(
           key, Context.MODE_PRIVATE)
    }
}