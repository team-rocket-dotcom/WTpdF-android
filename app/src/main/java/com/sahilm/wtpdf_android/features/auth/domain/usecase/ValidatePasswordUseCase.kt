package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationResult
import com.sahilm.wtpdf_android.features.auth.util.BaseUseCase
import com.sahilm.wtpdf_android.features.auth.util.UiText
import com.sahilm.wtpdf_android.features.auth.util.isPasswordValid

class ValidatePasswordUseCase : BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.length < 8) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resID = R.string.password_smaller_than_eight)
            )
        }
        if (!isPasswordValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resID = R.string.password_not_valid)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}