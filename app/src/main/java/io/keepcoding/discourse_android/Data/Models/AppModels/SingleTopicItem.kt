package io.keepcoding.discourse_android.Data.Models.AppModels

import io.keepcoding.discourse_android.Data.Models.ResponseModels.CreatedBy
import io.keepcoding.discourse_android.Data.Models.ResponseModels.PostsItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.SingleTopicResponse
import io.keepcoding.discourse_android.Utils
import java.io.Serializable
import java.util.*

data class SingleTopicItem (
        val id: String = UUID.randomUUID().toString(),
        val title: String = "",
        val date: Date = Date(),
        val postCount: Int = 0,
        val views: Int = 0,
        val replies: Int = 0,
        val poster: Poster? = null,
        val posts: List<PostItem>? = null,
    ): Serializable {

    companion object {
        var utils = Utils()

        fun parseTopic(response: SingleTopicResponse): SingleTopicItem {
            val poster = parseUser(response.details?.createdBy)
            val postList = response.postStream?.posts!!
            var posts = mutableListOf<PostItem>()
            var date = utils.formatDate(response.createdAt)

            for (post in postList) {
                val parsedPost = parsePost(post)
                posts.add(parsedPost)
            }

            return SingleTopicItem(
                    id = response.id.toString(),
                    title = response.title.toString(),
                    date = date,
                    postCount = response.postsCount ?: 0,
                    views = response.views ?: 0,
                    replies = response.replyCount ?: 0,
                    poster = poster,
                    posts = posts
            )
        }

        fun parseUser(user: CreatedBy?): Poster {
            val avatarTemplate = user?.avatarTemplate
            val userURL = utils.getURL(avatarTemplate)

            return Poster(
                    username = user?.username ?: "",
                    URL = userURL
            )
        }

        fun parsePost(post: PostsItem?): PostItem {
            val date = utils.formatDate(post?.createdAt)
            val avatarTemplate = post?.avatarTemplate
            val url = utils.getURL(avatarTemplate)
            val postContent = post?.cooked.toString()

            return PostItem(
                    username = post?.username.toString(),
                    URL = url,
                    date = date,
                    content = postContent
            )
        }
    }
}
