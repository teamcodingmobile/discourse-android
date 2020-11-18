package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class GetUserResponse(

	@field:SerializedName("can_impersonate")
	val canImpersonate: Boolean? = null,

	@field:SerializedName("flags_given_count")
	val flagsGivenCount: Int? = null,

	@field:SerializedName("can_grant_admin")
	val canGrantAdmin: Boolean? = null,

	@field:SerializedName("can_disable_second_factor")
	val canDisableSecondFactor: Boolean? = null,

	@field:SerializedName("moderator")
	val moderator: Boolean? = null,

	@field:SerializedName("reset_bounce_score_after")
	val resetBounceScoreAfter: Any? = null,

	@field:SerializedName("trust_level")
	val trustLevel: Int? = null,

	@field:SerializedName("can_revoke_moderation")
	val canRevokeModeration: Boolean? = null,

	@field:SerializedName("manual_locked_trust_level")
	val manualLockedTrustLevel: Any? = null,

	@field:SerializedName("can_revoke_admin")
	val canRevokeAdmin: Boolean? = null,

	@field:SerializedName("can_activate")
	val canActivate: Boolean? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("can_delete_all_posts")
	val canDeleteAllPosts: Boolean? = null,

	@field:SerializedName("silenced_by")
	val silencedBy: Any? = null,

	@field:SerializedName("days_visited")
	val daysVisited: Int? = null,

	@field:SerializedName("flag_level")
	val flagLevel: Int? = null,

	@field:SerializedName("can_be_anonymized")
	val canBeAnonymized: Boolean? = null,

	@field:SerializedName("single_sign_on_record")
	val singleSignOnRecord: Any? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("like_given_count")
	val likeGivenCount: Int? = null,

	@field:SerializedName("suspended")
	val suspended: Boolean? = null,

	@field:SerializedName("private_topics_count")
	val privateTopicsCount: Int? = null,

	@field:SerializedName("approved_by")
	val approvedBy: Any? = null,

	@field:SerializedName("created_at_age")
	val createdAtAge: Double? = null,

	@field:SerializedName("bounce_score")
	val bounceScore: Double? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("last_emailed_at")
	val lastEmailedAt: String? = null,

	@field:SerializedName("post_count")
	val postCount: Int? = null,

	@field:SerializedName("badge_count")
	val badgeCount: Int? = null,

	@field:SerializedName("silence_reason")
	val silenceReason: Any? = null,

	@field:SerializedName("primary_group_id")
	val primaryGroupId: Any? = null,

	@field:SerializedName("can_view_action_logs")
	val canViewActionLogs: Boolean? = null,

	@field:SerializedName("username_lower")
	val usernameLower: String? = null,

	@field:SerializedName("admin")
	val admin: Boolean? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("last_seen_age")
	val lastSeenAge: Double? = null,

	@field:SerializedName("posts_read_count")
	val postsReadCount: Int? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("warnings_received_count")
	val warningsReceivedCount: Int? = null,

	@field:SerializedName("full_suspend_reason")
	val fullSuspendReason: Any? = null,

	@field:SerializedName("registration_ip_address")
	val registrationIpAddress: String? = null,

	@field:SerializedName("suspended_by")
	val suspendedBy: Any? = null,

	@field:SerializedName("can_be_deleted")
	val canBeDeleted: Boolean? = null,

	@field:SerializedName("last_emailed_age")
	val lastEmailedAge: Double? = null,

	@field:SerializedName("can_deactivate")
	val canDeactivate: Boolean? = null,

	@field:SerializedName("avatar_template")
	val avatarTemplate: String? = null,

	@field:SerializedName("time_read")
	val timeRead: Int? = null,

	@field:SerializedName("groups")
	val groups: List<GetUserResponseGroup?>? = null,

	@field:SerializedName("staged")
	val staged: Boolean? = null,

	@field:SerializedName("ip_address")
	val ipAddress: String? = null,

	@field:SerializedName("flags_received_count")
	val flagsReceivedCount: Int? = null,

	@field:SerializedName("topic_count")
	val topicCount: Int? = null,

	@field:SerializedName("can_grant_moderation")
	val canGrantModeration: Boolean? = null,

	@field:SerializedName("can_send_activation_email")
	val canSendActivationEmail: Boolean? = null,

	@field:SerializedName("topics_entered")
	val topicsEntered: Int? = null,

	@field:SerializedName("last_seen_at")
	val lastSeenAt: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class GetUserResponseGroup(

	@field:SerializedName("mentionable_level")
	val mentionableLevel: Int? = null,

	@field:SerializedName("public_admission")
	val publicAdmission: Boolean? = null,

	@field:SerializedName("primary_group")
	val primaryGroup: Boolean? = null,

	@field:SerializedName("flair_color")
	val flairColor: Any? = null,

	@field:SerializedName("title")
	val title: Any? = null,

	@field:SerializedName("visibility_level")
	val visibilityLevel: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("allow_membership_requests")
	val allowMembershipRequests: Boolean? = null,

	@field:SerializedName("automatic_membership_email_domains")
	val automaticMembershipEmailDomains: Any? = null,

	@field:SerializedName("messageable_level")
	val messageableLevel: Int? = null,

	@field:SerializedName("automatic_membership_retroactive")
	val automaticMembershipRetroactive: Boolean? = null,

	@field:SerializedName("grant_trust_level")
	val grantTrustLevel: Any? = null,

	@field:SerializedName("automatic")
	val automatic: Boolean? = null,

	@field:SerializedName("bio_raw")
	val bioRaw: Any? = null,

	@field:SerializedName("flair_url")
	val flairUrl: Any? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

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

	@field:SerializedName("incoming_email")
	val incomingEmail: Any? = null,

	@field:SerializedName("has_messages")
	val hasMessages: Boolean? = null,

	@field:SerializedName("bio_cooked")
	val bioCooked: Any? = null
)
