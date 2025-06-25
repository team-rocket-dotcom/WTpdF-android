package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationResult
import com.sahilm.wtpdf_android.features.auth.util.BaseUseCase
import com.sahilm.wtpdf_android.features.auth.util.UiText

class ValidateConfirmPasswordUseCase : BaseUseCase<Params, ValidationResult> {
    override fun execute(input: Params): ValidationResult {
        if (input.confirmPassword.isEmpty()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resID = R.string.confirm_password_cannot_be_blank)
            )
        }

        if (input.confirmPassword != input.password) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resID = R.string.this_password_must_be_same)
            )
        }

        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }

}

data class Params(val password: String, val confirmPassword: String)