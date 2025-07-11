package com.sahilm.wtpdf_android.features.auth.data.model

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    val access: String,
    val refresh: String,
    val user: UserDataEntity,
    val message: String
)


