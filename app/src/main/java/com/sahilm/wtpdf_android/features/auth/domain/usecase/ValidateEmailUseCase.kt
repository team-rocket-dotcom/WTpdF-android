package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationResult
import com.sahilm.wtpdf_android.features.auth.util.BaseUseCase
import com.sahilm.wtpdf_android.features.auth.util.UiText
import com.sahilm.wtpdf_android.features.auth.util.isEmailValid

class ValidateEmailUseCase: BaseUseCase<String, ValidationResult> {
    override fun execute(input: String): ValidationResult {
        if (input.isBlank()) {
            return ValidationResult(
                successful =  false,
                errorMessage = UiText.StringResource(resID = R.string.email_cannot_be_blank)
            )
        }
        if (!isEmailValid(input)) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(resID = R.string.email_not_valid)
            )
        }
        return ValidationResult(
            successful = true,
            errorMessage = null
        )
    }
}