package com.sahilm.wtpdf_android.features.auth.domain.model

import com.google.gson.annotations.SerializedName

data class UserData(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val authProvider: String,
    val picture: String? = null
)
