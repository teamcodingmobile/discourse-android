package io.keepcoding.discourse_android

import io.keepcoding.discourse_android.Data.Models.AppModels.Poster
import io.keepcoding.discourse_android.Data.Models.AppModels.TopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.TopicsItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.UsersItem
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TopicItemModelTest {

    @Test
    fun parseUserIsCorrect() {
        val userTest = UsersItem(
            avatarTemplate = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png",
            username = "testUsername"
        )

        val user = TopicItem.parseUser(userTest)

        assertEquals("testUsername", user.username)
        assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", user.URL)
    }

    @Test
    fun parseTopicIsCorrect() {
        val utils = Utils()
        val testTopic = TopicsItem (
                id = 7,
                title = "Welcome to Discourse",
                createdAt = "2019-12-12T01:41:28.809Z",
                postsCount = 1,
                replyCount = 0,
                views = 0,
        )
        val testPoster = Poster (
                username = "userTest",
                id = 1,
                URL = "URLTest"
        )

        val topic = TopicItem.parseTopic(testTopic, testPoster)

        assertEquals("7", topic.id)
        assertEquals("Welcome to Discourse", topic.title)
        assertEquals(1, topic.posts)
        assertEquals(0, topic.views)
        assertEquals("userTest", topic.poster?.username)
        assertEquals("URLTest", topic.poster?.URL)

    }

}