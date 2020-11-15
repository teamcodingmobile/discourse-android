package io.keepcoding.discourse_android.Data.Models.AppModels

data class SignUpModel(
        val name: String,
        val username: String,
        val email: String,
        val password: String,
        val active: Boolean = true,
        val approved: Boolean = true
)