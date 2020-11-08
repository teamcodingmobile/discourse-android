package io.keepcoding.discourse_android

import io.keepcoding.discourse_android.Data.Models.*
import junit.framework.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TopicItemModelTest {
   @Test
   fun getOffset_year_isCorrect() {
       val dateToCompare: Date = formatDate("01/01/2020 10:00:00")
       val testTopic = TopicItem(
           title = "Test",
           date = formatDate("01/01/2019 10:00:00")
       )

       val offSet = testTopic.getTimeOffset(dateToCompare)
       assertEquals("Amount comparision", 1, offSet.amount)
       assertEquals("Unit comparison", Calendar.YEAR, offSet.unit)
   }

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
                URL = "URLTest"
        )

        val topic = TopicItem.parseTopic(testTopic, testPoster)

        assertEquals("7", topic.id)
        assertEquals("Welcome to Discourse", topic.title)
        assertEquals(1, topic.posts)
        assertEquals(0, topic.views)
        assertEquals("userTest", topic.poster?.username)
        assertEquals("URLTest", topic.poster?.URL)

        // "2019-12-12T01:41:28.809Z"
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        val dateFormatted = formatter.format(topic.date)  //12/12/2019 01:41:28
        assertEquals("12/12/2019 02:41:28", dateFormatted)

    }

    private fun formatDate(date: String) : Date {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
        return formatter.parse(date) ?: throw IllegalArgumentException("Date $date has an incorrect format, try again with format dd/MM/yyyy hh:mm:ss")
    }

}