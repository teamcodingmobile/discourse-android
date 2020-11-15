package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class SignInResponse(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("user_badges")
	val userBadges: List<Any?>? = null
)

data class CustomFields(
	val any: Any? = null
)

data class UserOption(

	@field:SerializedName("email_messages_level")
	val emailMessagesLevel: Int? = null,

	@field:SerializedName("title_count_mode")
	val titleCountMode: String? = null,

	@field:SerializedName("email_level")
	val emailLevel: Int? = null,

	@field:SerializedName("dynamic_favicon")
	val dynamicFavicon: Boolean? = null,

	@field:SerializedName("theme_key_seq")
	val themeKeySeq: Int? = null,

	@field:SerializedName("theme_ids")
	val themeIds: List<Int?>? = null,

	@field:SerializedName("email_digests")
	val emailDigests: Boolean? = null,

	@field:SerializedName("hide_profile_and_presence")
	val hideProfileAndPresence: Boolean? = null,

	@field:SerializedName("homepage_id")
	val homepageId: Any? = null,

	@field:SerializedName("digest_after_minutes")
	val digestAfterMinutes: Int? = null,

	@field:SerializedName("auto_track_topics_after_msecs")
	val autoTrackTopicsAfterMsecs: Int? = null,

	@field:SerializedName("new_topic_duration_minutes")
	val newTopicDurationMinutes: Int? = null,

	@field:SerializedName("notification_level_when_replying")
	val notificationLevelWhenReplying: Int? = null,

	@field:SerializedName("allow_private_messages")
	val allowPrivateMessages: Boolean? = null,

	@field:SerializedName("text_size_seq")
	val textSizeSeq: Int? = null,

	@field:SerializedName("like_notification_frequency")
	val likeNotificationFrequency: Int? = null,

	@field:SerializedName("enable_quoting")
	val enableQuoting: Boolean? = null,

	@field:SerializedName("automatically_unpin_topics")
	val automaticallyUnpinTopics: Boolean? = null,

	@field:SerializedName("enable_defer")
	val enableDefer: Boolean? = null,

	@field:SerializedName("email_in_reply_to")
	val emailInReplyTo: Boolean? = null,

	@field:SerializedName("text_size")
	val textSize: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("mailing_list_mode_frequency")
	val mailingListModeFrequency: Int? = null,

	@field:SerializedName("mailing_list_mode")
	val mailingListMode: Boolean? = null,

	@field:SerializedName("include_tl0_in_digests")
	val includeTl0InDigests: Boolean? = null,

	@field:SerializedName("external_links_in_new_tab")
	val externalLinksInNewTab: Boolean? = null,

	@field:SerializedName("email_previous_replies")
	val emailPreviousReplies: Int? = null
)

data class GroupsItem(

	@field:SerializedName("mentionable_level")
	val mentionableLevel: Int? = null,

	@field:SerializedName("messageable_level")
	val messageableLevel: Int? = null,

	@field:SerializedName("public_admission")
	val publicAdmission: Boolean? = null,

	@field:SerializedName("primary_group")
	val primaryGroup: Boolean? = null,

	@field:SerializedName("grant_trust_level")
	val grantTrustLevel: Any? = null,

	@field:SerializedName("flair_color")
	val flairColor: Any? = null,

	@field:SerializedName("automatic")
	val automatic: Boolean? = null,

	@field:SerializedName("flair_url")
	val flairUrl: Any? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("public_exit")
	val publicExit: Boolean? = null,

	@field:SerializedName("bio_excerpt")
	val bioExcerpt: Any? = null,

	@field:SerializedName("flair_bg_color")
	val flairBgColor: Any? = null,

	@field:SerializedName("full_name")
	val fullName: Any? = null,

	@field:SerializedName("default_notification_level")
	val defaultNotificationLevel: Int? = null,

	@field:SerializedName("user_count")
	val userCount: Int? = null,

	@field:SerializedName("membership_request_template")
	val membershipRequestTemplate: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("visibility_level")
	val visibilityLevel: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("has_messages")
	val hasMessages: Boolean? = null,

	@field:SerializedName("allow_membership_requests")
	val allowMembershipRequests: Boolean? = null,

	@field:SerializedName("bio_cooked")
	val bioCooked: Any? = null
)

data class GroupUsersItem(

	@field:SerializedName("owner")
	val owner: Boolean? = null,

	@field:SerializedName("group_id")
	val groupId: Int? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("notification_level")
	val notificationLevel: Int? = null
)
