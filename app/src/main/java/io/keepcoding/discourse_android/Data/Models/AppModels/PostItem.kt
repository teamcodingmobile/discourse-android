package io.keepcoding.discourse_android.Data.Models.AppModels

import java.util.*

data class PostItem (
        val username: String = "",
        val URL: String = "",
        val date: Date = Date(),
        val content: String = ""
)

