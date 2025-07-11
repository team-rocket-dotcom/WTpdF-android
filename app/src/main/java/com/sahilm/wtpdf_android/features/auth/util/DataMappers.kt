package com.sahilm.wtpdf_android.features.auth.util

import com.sahilm.wtpdf_android.features.auth.data.model.UserDataEntity
import com.sahilm.wtpdf_android.features.auth.domain.model.UserData

fun UserDataEntity.toModel() = UserData (
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    authProvider = authProvider
)

fun UserData.toEntity()  = UserDataEntity (
    id = id,
    firstName = firstName,
    lastName = lastName,
    email = email,
    authProvider = authProvider
)