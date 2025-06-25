package com.sahilm.wtpdf_android.features.auth.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sahilm.wtpdf_android.features.auth.domain.usecase.Params
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateConfirmPasswordUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateEmailUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidatePasswordUseCase
import com.sahilm.wtpdf_android.features.auth.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ValidationViewModel @Inject constructor(): ViewModel() {

    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()
    private val validateConfirmPasswordUseCase = ValidateConfirmPasswordUseCase()

    var formState by mutableStateOf(ValidationState())

    fun onEvent(event: ValidationEvent) {
        when (event) {
            is ValidationEvent.UsernameChanged -> {
                formState = formState.copy(userName = event.userName)
            }

            is ValidationEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is ValidationEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is ValidationEvent.ConfirmPasswordChanged -> {
                formState = formState.copy(confirmPassword = event.confirmPassword)
                validateConfirmPassword()
            }

            is ValidationEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isVisiblePassword)
            }

            is ValidationEvent.Submit -> {
                if (validateEmail() && validatePassword()) {

                }
            }
        }
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(formState.password)
        formState = formState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateConfirmPassword(): Boolean {
        val params = Params(formState.password, formState.confirmPassword)
        val confirmPasswordResult = validateConfirmPasswordUseCase.execute(params)
        formState = formState.copy(confirmPasswordError = confirmPasswordResult.errorMessage)
        return confirmPasswordResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(formState.email)
        formState = formState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

}

sealed class ValidationEvent {
    data class UsernameChanged(val userName: String) : ValidationEvent()
    data class EmailChanged(val email: String) : ValidationEvent()
    data class PasswordChanged(val password: String) : ValidationEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : ValidationEvent()
    data class VisiblePassword(val isVisiblePassword: Boolean) : ValidationEvent()
    object Submit : ValidationEvent()
}

data class ValidationState(
    val userName: String = "",
    val email: String = "",
    val emailError: UiText? = null,
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: UiText? = null,
    val confirmPasswordError: UiText? = null,
    val isVisiblePassword: Boolean = false
)