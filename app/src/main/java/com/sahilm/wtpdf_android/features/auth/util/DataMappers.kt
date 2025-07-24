package com.sahilm.wtpdf_android.features.auth.util

import com.sahilm.wtpdf_android.features.auth.data.model.SignUpUserDataEntity
import com.sahilm.wtpdf_android.features.auth.domain.model.UserData

fun SignUpUserDataEntity.toModel() = UserData (
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    authProvider = authProvider
)

fun UserData.toEntity()  = SignUpUserDataEntity (
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    authProvider = authProvider
)