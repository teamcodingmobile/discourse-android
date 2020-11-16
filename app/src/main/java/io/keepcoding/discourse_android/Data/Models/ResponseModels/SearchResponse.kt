package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("topics")
	val topics: List<TopicsItemSearch?>? = null,

	@field:SerializedName("posts")
	val posts: List<PostsItemSearch?>? = null,

	@field:SerializedName("users")
	val users: List<UsersItemSearch?>? = null
)


data class UsersItemSearch(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String
)


data class TopicsItemSearch(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("posts_count")
	val postsCount: String,

	@field:SerializedName("reply_count")
	val replyCount: String,

	@field:SerializedName("last_posted_at")
	val lastPostedAt: String,

	@field:SerializedName("pinned")
	val pinned: Boolean
)


data class PostsItemSearch(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String,

	@field:SerializedName("blurb")
	val blurb: String
)
