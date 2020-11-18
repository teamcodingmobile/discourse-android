package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class GetUserTopicsResponse(

	@field:SerializedName("primary_groups")
	val primaryGroups: List<Any?>? = null,

	@field:SerializedName("topic_list")
	val topicList: GetUserTopicsResponseTopicList? = null,

	@field:SerializedName("users")
	val users: List<GetUserTopicsResponseUsersItem?>? = null
)

data class GetUserTopicsResponseTopicList(

	@field:SerializedName("can_create_topic")
	val canCreateTopic: Boolean? = null,

	@field:SerializedName("per_page")
	val perPage: Int? = null,

	@field:SerializedName("topics")
	val topics: List<GetUserTopicsResponseTopicsItem?>? = null,

	@field:SerializedName("draft")
	val draft: Any? = null,

	@field:SerializedName("draft_sequence")
	val draftSequence: Int? = null,

	@field:SerializedName("draft_key")
	val draftKey: String? = null
)

data class GetUserTopicsResponsePostersItem(

	@field:SerializedName("primary_group_id")
	val primaryGroupId: Any? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("extras")
	val extras: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)

data class GetUserTopicsResponseUsersItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class GetUserTopicsResponseTopicsItem(

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
	val liked: Any? = null,

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
	val imageUrl: Any? = null,

	@field:SerializedName("bookmarked")
	val bookmarked: Any? = null,

	@field:SerializedName("last_poster_username")
	val lastPosterUsername: String? = null,

	@field:SerializedName("posters")
	val posters: List<GetUserTopicsResponsePostersItem?>? = null,

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
	val postsCount: Int? = null
)
