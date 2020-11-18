package io.keepcoding.discourse_android.Data.Models.AppModels

import io.keepcoding.discourse_android.Data.Models.ResponseModels.*
import io.keepcoding.discourse_android.Utils
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
        var utils = Utils()


            fun parseTopicsList(response: LatestTopicResponse): MutableList<TopicItem> {
            val objectList = response.topicList?.topics!!

            val topics = mutableListOf<TopicItem>()

            val usersList = response.users!!
            val users = mutableListOf<Poster>()

            for (user in usersList) {
                val parsedUser = parseUser(user)
                users.add(parsedUser)
            }

            for (topic in topicsList) {

                val posterList = topic?.posters

                var topicposterObject = posterList?.first { it?.description!!.contains("Original") }

                val topicPoster = topicposterObject?.userId

                val poster = users.first { it.id == topicPoster }

                val parsedTopic = parseTopic(topic, poster)
                topics.add(parsedTopic)
            }

            return topics
        }

        fun parseTopicsList(response: GetUserTopicsResponse): List<TopicItem> {
            val topicsList = response.topicList?.topics!!
            val topics = mutableListOf<TopicItem>()

            val usersList = response.users!!
            val users = mutableListOf<Poster>()

            for (user in usersList) {
                val parsedUser = parseUser(user)
                users.add(parsedUser)
            }

            for (topic in topicsList) {

                val posterList = topic?.posters

                var topicposterObject = posterList?.first { it?.description!!.contains("Original") }

                val topicPoster = topicposterObject?.userId

                val poster = users.first { it.id == topicPoster }

                val parsedTopic = parseTopic(topic, poster)
                topics.add(parsedTopic)
            }

            return topics
        }

        fun parseUser (user: UsersItem?): Poster {
            val avatarTemplate = user?.avatarTemplate
            val userURL = utils.getURL(avatarTemplate)

            return Poster (
                    username = user?.username ?: "",
                    id = user?.id ?: 0,
                    URL = userURL
            )
        }

        fun parseUser (user: GetUserTopicsResponseUsersItem?): Poster {
            val avatarTemplate = user?.avatarTemplate
            val userURL = utils.getURL(avatarTemplate)

            return Poster (
                username = user?.username ?: "",
                id = user?.id ?: 0,
                URL = userURL
            )
        }

        fun parseTopic(topic: TopicsItem?, poster: Poster?): TopicItem {
            val date = utils.formatDate(topic?.createdAt)
            return TopicItem(
                    id = topic?.id.toString(),
                    title = topic?.title ?: "",
                    date = date,
                    posts = topic?.postsCount ?: 0,
                    views = topic?.views ?: 0,
                    replies = topic?.replyCount ?: 0,
                    poster = poster
            )
        }

        fun parseTopic(topic: GetUserTopicsResponseTopicsItem?, poster: Poster?): TopicItem {
            val date = utils.formatDate(topic?.createdAt)
            return TopicItem(
                id = topic?.id.toString(),
                title = topic?.title ?: "",
                date = date,
                posts = topic?.postsCount ?: 0,
                views = topic?.views ?: 0,
                replies = topic?.replyCount ?: 0,
                poster = poster
            )
        }
    }
}