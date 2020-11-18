package io.keepcoding.discourse_android.Data

import android.content.Context

const val PREFERENCES_SESSION = "session"
const val PREFERENCES_USERNAME = "username"
const val PREFERENCES_ID = "id"

class LoginService(private val context: Context) {

    fun saveSession(id: Int, username: String) {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        preferences
                .edit()
                .putInt(PREFERENCES_ID, id)
                .putString(PREFERENCES_USERNAME, username)
                .apply()
    }

    fun getUserId(): Int {
        return context
            .getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
            .getInt(PREFERENCES_ID, 0)
    }

    fun getUsername(): String? {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        return preferences.getString(PREFERENCES_USERNAME, null)
    }

    fun logout() {
        val preferences = context.getSharedPreferences(PREFERENCES_SESSION, Context.MODE_PRIVATE)
        preferences
                .edit()
                .remove(PREFERENCES_ID)
                .putString(PREFERENCES_USERNAME, null)
                .apply()
    }

    fun isLogged(): Boolean {
        return 0 != getUserId() && getUsername() != null
    }
}