package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class PostResponse(

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
	val score: Int? = null,

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

	@field:SerializedName("draft_sequence")
	val draftSequence: Int? = null,

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

	@field:SerializedName("edit_reason")
	val editReason: Any? = null,

	@field:SerializedName("primary_group_flair_url")
	val primaryGroupFlairUrl: Any? = null,

	@field:SerializedName("hidden_reason_id")
	val hiddenReasonId: Any? = null,

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

	@field:SerializedName("avg_time")
	val avgTime: Any? = null,

	@field:SerializedName("display_username")
	val displayUsername: String? = null
)

