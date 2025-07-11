package com.sahilm.wtpdf_android.features.auth.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

@Serializable
data class UserPreferences(
    val refreshToken: String? = null,
    val accessToken: String? = null,
    val email: String? = null,
    val password: String? = null
)
