package com.sahilm.wtpdf_android.features.auth.data.model

data class GoogleSignInResponse(
    val access: String,
    val refresh: String,
    val user: GoogleSignInUserDataEntity,
    val message: String
)
