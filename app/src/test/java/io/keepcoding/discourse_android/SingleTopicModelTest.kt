package io.keepcoding.discourse_android

import io.keepcoding.discourse_android.Data.Models.AppModels.PostItem
import io.keepcoding.discourse_android.Data.Models.AppModels.SingleTopicItem
import io.keepcoding.discourse_android.Data.Models.ResponseModels.*
import org.junit.Test

import org.junit.Assert
import java.util.*

class SingleTopicModelTest {

    @Test
    fun parseUserIsCorrect() {
        val userTest = CreatedBy(
                avatarTemplate = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png",
                id = 10,
                username = "testUsername"
        )

        val user = SingleTopicItem.parseUser(userTest)

        Assert.assertEquals("testUsername", user.username)
        Assert.assertEquals(10, user.id)
        Assert.assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", user.URL)
    }

    @Test
    fun parsePostIsCorrect() {
        val postTest = PostsItem(
                username = "testUsername",
                avatarTemplate = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png",
                createdAt = "2019-12-12T01:41:28.809Z",
                cooked = "testContent"
        )

        val post = SingleTopicItem.parsePost(postTest)

        Assert.assertEquals("testUsername", post.username)
        Assert.assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", post.URL)
        Assert.assertEquals("testContent", post.content)

    }

    @Test
    fun parseTopicIsCorrect() {
        val utils = Utils()
        val testResponse = SingleTopicResponse (
                details = Details(
                        createdBy = CreatedBy (
                                username = "userTest",
                                avatarTemplate = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png"
                        )
                ),
                postStream = PostStream(
                        posts = listOf(PostsItem(
                                username = "postUsername",
                                avatarTemplate = "/user_avatar/mdiscourse.keepcoding.io/system/{size}/1_2.png",
                                createdAt = "2019-12-12T01:41:28.809Z",
                                cooked = "")
                        )),
                createdAt = "2019-12-12T01:41:28.809Z",
                id = 7,
                title = "Welcome to Discourse",
                postsCount = 1,
                replyCount = 0,
                views = 0,
        )

        val topic = SingleTopicItem.parseTopic(testResponse)

        Assert.assertEquals("7", topic.id)
        Assert.assertEquals("Welcome to Discourse", topic.title)
        Assert.assertEquals(1, topic.postCount)
        Assert.assertEquals(0, topic.views)
        Assert.assertEquals(0, topic.replies)
        Assert.assertEquals("userTest", topic.poster?.username)
        Assert.assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", topic.poster?.URL)
        Assert.assertEquals("https://mdiscourse.keepcoding.io/user_avatar/mdiscourse.keepcoding.io/system/80/1_2.png", topic.posts?.get(0)?.URL)

    }



}