package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class SignUpResponse(

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("active")
	val active: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)
