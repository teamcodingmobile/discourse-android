package io.keepcoding.discourse_android.Data.Models.AppModels

import java.io.Serializable

data class Poster (
        val username: String = "",
        val id: Int,
        val URL: String = ""
): Serializable