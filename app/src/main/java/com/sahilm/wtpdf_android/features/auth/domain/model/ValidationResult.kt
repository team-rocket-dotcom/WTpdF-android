package com.sahilm.wtpdf_android.features.auth.domain.model

import com.sahilm.wtpdf_android.features.auth.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage:  UiText?
)
