package io.keepcoding.discourse_android.models

import java.io.Serializable


data class LatestTopicsResponse(
	val primaryGroups: List<Any?>? = null,
	val topicList: TopicList? = null,
	val users: List<UsersItem?>? = null
) : Serializable

data class TopicList(
	val canCreateTopic: Boolean? = null,
	val perPage: Int? = null,
	val moreTopicsUrl: String? = null,
	val topics: List<TopicsItem?>? = null,
	val draft: Any? = null,
	val draftSequence: Int? = null,
	val draftKey: String? = null
) : Serializable


data class TopicsItem(
	val unpinned: Any? = null,
	val pinned: Boolean? = null,
	val featuredLink: Any? = null,
	val createdAt: String? = null,
	val bumped: Boolean? = null,
	val title: String? = null,
	val liked: Boolean? = null,
	val archived: Boolean? = null,
	val hasSummary: Boolean? = null,
	val fancyTitle: String? = null,
	val categoryId: Int? = null,
	val id: Int? = null,
	val bumpedAt: String? = null,
	val slug: String? = null,
	val views: Int? = null,
	val lastPostedAt: String? = null,
	val visible: Boolean? = null,
	val likeCount: Int? = null,
	val imageUrl: String? = null,
	val bookmarked: Boolean? = null,
	val lastPosterUsername: String? = null,
	val posters: List<PostersItem?>? = null,
	val pinnedGlobally: Boolean? = null,
	val replyCount: Int? = null,
	val archetype: String? = null,
	val highestPostNumber: Int? = null,
	val closed: Boolean? = null,
	val unseen: Boolean? = null,
	val postsCount: Int? = null,
	val unread: Int? = null,
	val newPosts: Int? = null,
	val lastReadPostNumber: Int? = null,
	val notificationLevel: Int? = null,
	val excerpt: String? = null
) : Serializable


data class PostersItem(
	val primaryGroupId: Any? = null,
	val userId: Int? = null,
	val extras: String? = null,
	val description: String? = null
) : Serializable


data class UsersItem(
	val name: String? = null,
	val id: Int? = null,
	val avatarTemplate: String? = null,
	val username: String? = null
) : Serializable
