package com.sahilm.wtpdf_android.features.auth.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationState
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignInUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateEmailUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidatePasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SignInViewModel : ViewModel() {

    var formState by mutableStateOf(ValidationState())
    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()


    private fun validateEmail() : Boolean {
        val emailResult = validateEmailUseCase.execute(formState.email)
        formState = formState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    private fun validatePassword() : Boolean {
        val passwordResult = validatePasswordUseCase.execute(formState.password)
        formState = formState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    fun onEvent(event: SignInValidationEvent) {
        when (event) {
            is SignInValidationEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }
            is SignInValidationEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is SignInValidationEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isPasswordVisible)
            }
        }
    }

    fun onSignInClicked() {}

}

sealed class SignInValidationEvent {
    data class EmailChanged(val email: String) : SignInValidationEvent()
    data class PasswordChanged(val password: String) : SignInValidationEvent()
    data class VisiblePassword(val isPasswordVisible: Boolean) : SignInValidationEvent()
}

sealed class SignInUiEvent {
    data class ShowError(val message: String) : SignInUiEvent()
    object NavigateToSignUp : SignInUiEvent()
    object NavigateToHome : SignInUiEvent()
    object NavigateToAuth : SignInUiEvent()
}

data class SignInUiState (
    val isLoading: Boolean = false,
    val message: String? = null,
    val isError: Boolean = false
)