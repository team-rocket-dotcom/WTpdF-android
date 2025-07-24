package com.sahilm.wtpdf_android.features.auth.domain.model

import com.sahilm.wtpdf_android.features.auth.util.UiText

data class ValidationState(
    val userName: String = "",
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val isVisiblePassword: Boolean = false,
    val profileImageUri: String = ""
)