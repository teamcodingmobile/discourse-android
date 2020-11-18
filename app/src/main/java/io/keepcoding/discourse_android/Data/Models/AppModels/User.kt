package io.keepcoding.discourse_android.Data.Models.AppModels

import io.keepcoding.discourse_android.Data.Models.ResponseModels.GetUserResponse

data class User (
    val id: Int,
    val username: String,
    val name: String,
    val avatarTemplate: String?,
    val postsCount: Int,
    val topicsCount: Int,
    val likesCount: Int
) {
    companion object {
        fun parse(response: GetUserResponse): User {
            return User(
                id = response.id ?: 0,
                username = response.username ?: "unknown",
                name = response.name ?: "Unknown",
                avatarTemplate = response.avatarTemplate,
                postsCount = response.postCount ?: 0,
                topicsCount = response.topicCount ?: 0,
                likesCount = response.likeCount ?: 0
            )
        }
    }
}