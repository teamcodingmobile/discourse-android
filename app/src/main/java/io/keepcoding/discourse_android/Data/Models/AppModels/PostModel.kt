package io.keepcoding.discourse_android.Data.Models.AppModels

data class PostModel(
        val title: String?,
        val raw: String,
        val topic_id: Int? = null
)