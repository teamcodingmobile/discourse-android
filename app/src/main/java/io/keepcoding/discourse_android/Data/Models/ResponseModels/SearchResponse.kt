package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class SearchResponse(

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


data class PostsItemSearch(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String,

	@field:SerializedName("blurb")
	val blurb: String,

	@field:SerializedName("created_at")
	val date: String
)
