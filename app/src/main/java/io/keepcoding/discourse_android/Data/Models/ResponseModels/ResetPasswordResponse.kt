package io.keepcoding.discourse_android.Data.Models.ResponseModels

import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
	val success: String? = null,

	@field:SerializedName("user_found")
	val userFound: Boolean? = null
)

