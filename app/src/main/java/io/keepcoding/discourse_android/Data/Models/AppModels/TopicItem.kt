package io.keepcoding.discourse_android.Data.Models.AppModels

import io.keepcoding.discourse_android.Data.Models.ResponseModels.LatestTopicResponse
import io.keepcoding.discourse_android.Data.Models.ResponseModels.TopicsItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.UsersItem
import java.text.SimpleDateFormat
import java.util.*

data class TopicItem(
        val id: String = UUID.randomUUID().toString(),
        val title: String = "",
        val date: Date = Date(),
        val posts: Int = 0,
        val views: Int = 0,
        val replies: Int = 0,
        val poster: Poster? = null,
) {

    companion object {

        fun parseTopicsList(response: LatestTopicResponse): List<TopicItem> {
            val objectList = response.topicList?.topics!!
            val topics = mutableListOf<TopicItem>()

            val usersList = response.users!!
            val users = mutableListOf<Poster>()

            for (user in usersList) {
                val parsedUser = parseUser(user)
                users.add(parsedUser)
            }

            for (topic in objectList) {

                val topicPoster = topic?.lastPosterUsername

                val poster = users.first { it.username == topicPoster }

                val parsedTopic = parseTopic(topic, poster)
                topics.add(parsedTopic)
            }

            return topics
        }

        fun parseUser (user: UsersItem?): Poster {

            val avatarTemplate = user?.avatarTemplate
            val sized = avatarTemplate?.replace(oldValue = "{size}", newValue = "80")
            val userURL = "https://mdiscourse.keepcoding.io$sized"

            return Poster (
                    username = user?.username ?: "",
                    URL = userURL
            )
        }

        fun parseTopic(topic: TopicsItem?, poster: Poster?): TopicItem {
            val date = topic?.createdAt
                    ?.replace("Z", "+0000") ?: ""

            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.getDefault())
            val dateFormatted = dateFormat.parse(date) ?: Date()

            return TopicItem(
                    id = topic?.id.toString(),
                    title = topic?.title ?: "",
                    date = dateFormatted,
                    posts = topic?.postsCount ?: 0,
                    views = topic?.views ?: 0,
                    replies = topic?.replyCount ?: 0,
                    poster = poster
            )
        }
    }
}