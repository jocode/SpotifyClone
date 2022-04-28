package com.crexative.spotifyclone

import android.app.Application
import com.crexative.spotifyclone.core.AppPreferences

class SpotifyCloneApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}