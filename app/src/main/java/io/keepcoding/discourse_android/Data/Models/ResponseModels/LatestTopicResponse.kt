package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

import java.io.Serializable

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





