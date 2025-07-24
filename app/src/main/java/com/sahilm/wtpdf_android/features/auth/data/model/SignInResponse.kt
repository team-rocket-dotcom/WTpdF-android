package com.sahilm.wtpdf_android.features.auth.data.model

data class SignInResponse(
    val access: String,
    val refresh: String,
    val user: SignUpUserDataEntity,
    val message: String
)