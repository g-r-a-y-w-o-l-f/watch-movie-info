package com.org.watchmovie

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
@HiltAndroidApp
class WatchMovie: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}