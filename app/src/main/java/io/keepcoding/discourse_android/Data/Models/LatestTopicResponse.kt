package io.keepcoding.discourse_android.Data.Models

import com.google.gson.annotations.SerializedName

import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

data class LatestTopicResponse(

	@field:SerializedName("topic_list")
	val topicList: TopicList? = null,

	@field:SerializedName("users")
	val users: List<UsersItem?>? = null
): Serializable

data class UsersItem(
	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
): Serializable

data class TopicList(

	@field:SerializedName("can_create_topic")
	val canCreateTopic: Boolean? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("more_topics_url")
	val moreTopicsUrl: String? = null,

	@field:SerializedName("topics")
	val topics: List<TopicsItem?>? = null,

	@field:SerializedName("draft")
	val draft: Any? = null,

	@field:SerializedName("draft_sequence")
	val draftSequence: Int? = null,

	@field:SerializedName("draft_key")
	val draftKey: String? = null
): Serializable

data class TopicsItem(

	@field:SerializedName("unpinned")
	val unpinned: Any? = null,

	@field:SerializedName("pinned")
	val pinned: Boolean? = null,

	@field:SerializedName("featured_link")
	val featuredLink: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("bumped")
	val bumped: Boolean? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("liked")
	val liked: Boolean? = null,

	@field:SerializedName("archived")
	val archived: Boolean? = null,

	@field:SerializedName("has_summary")
	val hasSummary: Boolean? = null,

	@field:SerializedName("fancy_title")
	val fancyTitle: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("bumped_at")
	val bumpedAt: String? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("last_posted_at")
	val lastPostedAt: String? = null,

	@field:SerializedName("visible")
	val visible: Boolean? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("bookmarked")
	val bookmarked: Boolean? = null,

	@field:SerializedName("last_poster_username")
	val lastPosterUsername: String? = null,

	@field:SerializedName("posters")
	val posters: List<PostersItem?>? = null,

	@field:SerializedName("pinned_globally")
	val pinnedGlobally: Boolean? = null,

	@field:SerializedName("reply_count")
	val replyCount: Int? = null,

	@field:SerializedName("archetype")
	val archetype: String? = null,

	@field:SerializedName("highest_post_number")
	val highestPostNumber: Int? = null,

	@field:SerializedName("closed")
	val closed: Boolean? = null,

	@field:SerializedName("unseen")
	val unseen: Boolean? = null,

	@field:SerializedName("posts_count")
	val postsCount: Int? = null,

	@field:SerializedName("unread")
	val unread: Int? = null,

	@field:SerializedName("new_posts")
	val newPosts: Int? = null,

	@field:SerializedName("last_read_post_number")
	val lastReadPostNumber: Int? = null,

	@field:SerializedName("notification_level")
	val notificationLevel: Int? = null,

	@field:SerializedName("excerpt")
	val excerpt: String? = null
): Serializable

data class PostersItem(

	@field:SerializedName("primary_group_id")
	val primaryGroupId: Any? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("extras")
	val extras: Any? = null,

	@field:SerializedName("description")
	val description: String? = null
): Serializable

data class Poster (
		val username: String = "",
		val URL: String = ""
)

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

	val MINUTE_MILLIS = 1000L * 60
	val HOUR_MILLIS = MINUTE_MILLIS * 60
	val DAY_MILLIS = HOUR_MILLIS * 24
	val MONTH_MILLIS = DAY_MILLIS * 30
	val YEAR_MILLIS = MONTH_MILLIS * 12

	data class TimeOffset(val amount: Int, val unit: Int)

	fun getTimeOffset(dateToCompare: Date = Date()) : TimeOffset {
		val current = dateToCompare.time
		val diff = current - this.date.time

		val years = diff / YEAR_MILLIS
		if (years > 0) return TimeOffset(
				years.toInt(),
				Calendar.YEAR
		)

		val months = diff / MONTH_MILLIS
		if (months > 0) return TimeOffset(
				months.toInt(),
				Calendar.MONTH
		)

		val days = diff / DAY_MILLIS
		if (days > 0) return TimeOffset(
				days.toInt(),
				Calendar.DAY_OF_MONTH
		)

		val hours = diff / HOUR_MILLIS
		if (hours > 0) return TimeOffset(
				hours.toInt(),
				Calendar.HOUR
		)

		val minutes = diff / MINUTE_MILLIS
		if (minutes > 0) return TimeOffset(
				minutes.toInt(),
				Calendar.MINUTE
		)

		return TimeOffset(
				0,
				Calendar.MINUTE
		)
	}
}


