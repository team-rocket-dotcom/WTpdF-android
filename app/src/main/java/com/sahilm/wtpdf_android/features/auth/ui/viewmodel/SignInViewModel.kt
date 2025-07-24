package com.sahilm.wtpdf_android.features.auth.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationState
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignInUserParams
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignInUserUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateEmailUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidatePasswordUseCase
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import com.sahilm.wtpdf_android.features.auth.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUserUseCase: SignInUserUseCase
) : ViewModel() {

    var formState by mutableStateOf(ValidationState())
    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()
    private val _uiState = MutableStateFlow(SignInUiState())
    val uiState : StateFlow<SignInUiState> = _uiState.asStateFlow()
    private val _uiEvent = MutableStateFlow<SignInUiEvent?>(null)
    val uiEvent : StateFlow<SignInUiEvent?> = _uiEvent.asStateFlow()


    fun validateEmail() : Boolean {
        val emailResult = formState.email.isBlank()
        formState = formState.copy(emailError = UiText.StringResource(R.string.email_cannot_be_blank))
        return emailResult
    }

    fun validatePassword() : Boolean {
        val passwordResult = formState.password.isBlank()
        formState = formState.copy(passwordError = UiText.StringResource(R.string.fields_cannot_be_blank))
        return passwordResult
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

    fun onSignInClicked() {

        _uiState.value = _uiState.value.copy(isLoading = true, message = null)

        val isEmailValid = formState.email.isBlank()
        val isPasswordValid = formState.password.isBlank()

        if (!isEmailValid || !isPasswordValid) {
            formState = formState.copy(passwordError = UiText.StringResource(R.string.fields_cannot_be_blank))
        }
            viewModelScope.launch(Dispatchers.IO) {
                try {

                    val email = formState.email
                    val password = formState.password

                    val input = SignInUserParams(
                        email = email,
                        password = password
                    )

                    val result = signInUserUseCase.execute(input)

                    when (result) {
                        is ResultState.Success<*> -> {
                            _uiState.value =
                                _uiState.value.copy(isLoading = false, isSignInSuccessful = true)
                            _uiEvent.value = SignInUiEvent.NavigateToHome
                        }

                        is ResultState.Error<*> -> {
                            _uiState.value =
                                _uiState.value.copy(isLoading = false, message = result.message, isSignInSuccessful = false, isError = true)
                        }
                    }
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(isLoading = false, message = e.message)
                }
            }
        }
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
    val isError: Boolean = false,
    val isSignInSuccessful: Boolean = false
)