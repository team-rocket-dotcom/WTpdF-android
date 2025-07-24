package com.sahilm.wtpdf_android.features.auth.data.model

import com.google.gson.annotations.SerializedName

data class SignUpUserDataEntity(
val id: String,
val email: String,
@SerializedName("first_name")
val firstName: String,
@SerializedName("last_name")
val lastName: String,
@SerializedName("auth_provider")
val authProvider: String,
val picture: String? = null
)

