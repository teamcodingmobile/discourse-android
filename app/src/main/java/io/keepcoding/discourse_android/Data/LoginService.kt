package io.keepcoding.discourse_android.Data

import android.content.Context

const val PREFERENCES_SESSION = "session"
const val PREFERENCES_USERNAME = "username"

class LoginService {

    fun saveSession(context: Context, username: String) {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        preferences
                .edit()
                .putString(PREFERENCES_USERNAME, username)
                .apply()
    }

    fun getUsername(context: Context): String? {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        return preferences.getString(PREFERENCES_USERNAME, null)
    }

    fun logout(context: Context) {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        preferences
                .edit()
                .putString(PREFERENCES_USERNAME, null)
                .apply()
    }

    fun isLogged(context: Context): Boolean {
        val username = getUsername(context)
        return username != null
    }
}