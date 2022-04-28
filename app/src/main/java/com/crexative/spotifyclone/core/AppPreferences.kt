package com.crexative.spotifyclone.core

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private lateinit var pref: SharedPreferences
    private const val PREF_NAME = "SpotifyClone"
    private const val PREF_MODE = Context.MODE_PRIVATE

    fun init(context: Context) {
        pref = context.getSharedPreferences(PREF_NAME, PREF_MODE)
    }

    var token: String?
        get() = pref.getString(TOKEN, "")
        set(value) = pref.edit {
            it.putString(TOKEN, value)
        }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    private const val TOKEN = "spotify_token"

}