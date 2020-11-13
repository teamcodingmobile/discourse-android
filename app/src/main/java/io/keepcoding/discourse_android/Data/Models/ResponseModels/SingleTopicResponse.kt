package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class SingleTopicResponse(

	@field:SerializedName("post_stream")
	val postStream: PostStream? = null,

	@field:SerializedName("unpinned")
	val unpinned: Any? = null,

	@field:SerializedName("pinned")
	val pinned: Boolean? = null,

	@field:SerializedName("chunk_size")
	val chunkSize: Int? = null,

	@field:SerializedName("suggested_topics")
	val suggestedTopics: List<SuggestedTopicsItem?>? = null,

	@field:SerializedName("current_post_number")
	val currentPostNumber: Int? = null,

	@field:SerializedName("featured_link")
	val featuredLink: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("deleted_by")
	val deletedBy: Any? = null,

	@field:SerializedName("topic_timer")
	val topicTimer: Any? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("archived")
	val archived: Boolean? = null,

	@field:SerializedName("has_summary")
	val hasSummary: Boolean? = null,

	@field:SerializedName("private_topic_timer")
	val privateTopicTimer: Any? = null,

	@field:SerializedName("word_count")
	val wordCount: Int? = null,

	@field:SerializedName("fancy_title")
	val fancyTitle: String? = null,

	@field:SerializedName("category_id")
	val categoryId: Int? = null,

	@field:SerializedName("actions_summary")
	val actionsSummary: List<ActionsSummaryItem?>? = null,

	@field:SerializedName("pinned_at")
	val pinnedAt: Any? = null,

	@field:SerializedName("draft")
	val draft: Any? = null,

	@field:SerializedName("draft_sequence")
	val draftSequence: Int? = null,

	@field:SerializedName("draft_key")
	val draftKey: String? = null,

	@field:SerializedName("details")
	val details: Details? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("views")
	val views: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("last_posted_at")
	val lastPostedAt: String? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("visible")
	val visible: Boolean? = null,

	@field:SerializedName("message_bus_last_id")
	val messageBusLastId: Int? = null,

	@field:SerializedName("bookmarked")
	val bookmarked: Any? = null,

	@field:SerializedName("pinned_globally")
	val pinnedGlobally: Boolean? = null,

	@field:SerializedName("reply_count")
	val replyCount: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("archetype")
	val archetype: String? = null,

	@field:SerializedName("participant_count")
	val participantCount: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("pinned_until")
	val pinnedUntil: Any? = null,

	@field:SerializedName("highest_post_number")
	val highestPostNumber: Int? = null,

	@field:SerializedName("closed")
	val closed: Boolean? = null,

	@field:SerializedName("posts_count")
	val postsCount: Int? = null
)

data class SuggestedTopicsItem(

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
	val bookmarked: Boolean? = null,

	@field:SerializedName("posters")
	val posters: List<PostersItem?>? = null,

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
)

data class LastPoster(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class PostsItem(

	@field:SerializedName("hidden")
	val hidden: Boolean? = null,

	@field:SerializedName("can_wiki")
	val canWiki: Boolean? = null,

	@field:SerializedName("moderator")
	val moderator: Boolean? = null,

	@field:SerializedName("wiki")
	val wiki: Boolean? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("admin")
	val admin: Boolean? = null,

	@field:SerializedName("trust_level")
	val trustLevel: Int? = null,

	@field:SerializedName("score")
	val score: Double? = null,

	@field:SerializedName("can_view_edit_history")
	val canViewEditHistory: Boolean? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("actions_summary")
	val actionsSummary: List<ActionsSummaryItem?>? = null,

	@field:SerializedName("incoming_link_count")
	val incomingLinkCount: Int? = null,

	@field:SerializedName("can_delete")
	val canDelete: Boolean? = null,

	@field:SerializedName("primary_group_flair_bg_color")
	val primaryGroupFlairBgColor: Any? = null,

	@field:SerializedName("post_type")
	val postType: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("topic_id")
	val topicId: Int? = null,

	@field:SerializedName("quote_count")
	val quoteCount: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("primary_group_flair_color")
	val primaryGroupFlairColor: Any? = null,

	@field:SerializedName("read")
	val read: Boolean? = null,

	@field:SerializedName("edit_reason")
	val editReason: Any? = null,

	@field:SerializedName("primary_group_flair_url")
	val primaryGroupFlairUrl: Any? = null,

	@field:SerializedName("cooked")
	val cooked: String? = null,

	@field:SerializedName("reads")
	val reads: Int? = null,

	@field:SerializedName("can_edit")
	val canEdit: Boolean? = null,

	@field:SerializedName("staff")
	val staff: Boolean? = null,

	@field:SerializedName("reply_count")
	val replyCount: Int? = null,

	@field:SerializedName("reply_to_post_number")
	val replyToPostNumber: Any? = null,

	@field:SerializedName("version")
	val version: Int? = null,

	@field:SerializedName("deleted_at")
	val deletedAt: Any? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("primary_group_name")
	val primaryGroupName: Any? = null,

	@field:SerializedName("can_recover")
	val canRecover: Boolean? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("user_title")
	val userTitle: Any? = null,

	@field:SerializedName("user_deleted")
	val userDeleted: Boolean? = null,

	@field:SerializedName("post_number")
	val postNumber: Int? = null,

	@field:SerializedName("yours")
	val yours: Boolean? = null,

	@field:SerializedName("topic_slug")
	val topicSlug: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("display_username")
	val displayUsername: String? = null
)

data class ParticipantsItem(

	@field:SerializedName("primary_group_flair_color")
	val primaryGroupFlairColor: Any? = null,

	@field:SerializedName("primary_group_name")
	val primaryGroupName: Any? = null,

	@field:SerializedName("primary_group_flair_url")
	val primaryGroupFlairUrl: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("primary_group_flair_bg_color")
	val primaryGroupFlairBgColor: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("post_count")
	val postCount: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class Details(

	@field:SerializedName("last_poster")
	val lastPoster: LastPoster? = null,

	@field:SerializedName("notification_level")
	val notificationLevel: Int? = null,

	@field:SerializedName("can_create_post")
	val canCreatePost: Boolean? = null,

	@field:SerializedName("created_by")
	val createdBy: CreatedBy? = null,

	@field:SerializedName("participants")
	val participants: List<ParticipantsItem?>? = null
)

data class User(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class PostStream(

	@field:SerializedName("stream")
	val stream: List<Int?>? = null,

	@field:SerializedName("posts")
	val posts: List<PostsItem?>? = null
)

data class ActionsSummaryItem(

	@field:SerializedName("hidden")
	val hidden: Boolean? = null,

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("can_act")
	val canAct: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class CreatedBy(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
