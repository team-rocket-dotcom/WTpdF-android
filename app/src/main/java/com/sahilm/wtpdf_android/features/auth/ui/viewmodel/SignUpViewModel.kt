package com.sahilm.wtpdf_android.features.auth.ui.viewmodel

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahilm.wtpdf_android.features.auth.domain.model.ValidationState
import com.sahilm.wtpdf_android.features.auth.domain.usecase.PasswordParams
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignUpUserUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.UserParams
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateConfirmPasswordUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidateEmailUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.ValidatePasswordUseCase
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUserUseCase: SignUpUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState : StateFlow<SignUpUiState> = _uiState.asStateFlow()
    private val _uiEvent = MutableStateFlow<SignUpUiEvent?>(null)
    val uiEvent: StateFlow<SignUpUiEvent?> = _uiEvent.asStateFlow()
    
    private val validateEmailUseCase = ValidateEmailUseCase()
    private val validatePasswordUseCase = ValidatePasswordUseCase()
    private val validateConfirmPasswordUseCase = ValidateConfirmPasswordUseCase()
    var formState by mutableStateOf(ValidationState())


    fun onEvent(event: SignUpValidationEvent) {
        when (event) {
            is SignUpValidationEvent.UsernameChanged -> {
                formState = formState.copy(userName = event.userName)
            }

            is SignUpValidationEvent.EmailChanged -> {
                formState = formState.copy(email = event.email)
                validateEmail()
            }

            is SignUpValidationEvent.PasswordChanged -> {
                formState = formState.copy(password = event.password)
                validatePassword()
            }

            is SignUpValidationEvent.ConfirmPasswordChanged -> {
                formState = formState.copy(confirmPassword = event.confirmPassword)
                validateConfirmPassword()
            }

            is SignUpValidationEvent.VisiblePassword -> {
                formState = formState.copy(isVisiblePassword = event.isVisiblePassword)
            }
        }
    }

    fun onSignUpEvent(event: SignUpUiEvent) {
        when (event) {
            SignUpUiEvent.NavigateToHome -> TODO()
            SignUpUiEvent.NavigateToLogin -> TODO()
            is SignUpUiEvent.ShowError -> TODO()
            SignUpUiEvent.NavigateToAuth -> TODO()
        }
    }

    private fun validatePassword(): Boolean {
        val passwordResult = validatePasswordUseCase.execute(formState.password)
        formState = formState.copy(passwordError = passwordResult.errorMessage)
        return passwordResult.successful
    }

    private fun validateConfirmPassword(): Boolean {
        val params = PasswordParams(formState.password, formState.confirmPassword)
        val confirmPasswordResult = validateConfirmPasswordUseCase.execute(params)
        formState = formState.copy(confirmPasswordError = confirmPasswordResult.errorMessage)
        return confirmPasswordResult.successful
    }

    private fun validateEmail(): Boolean {
        val emailResult = validateEmailUseCase.execute(formState.email)
        formState = formState.copy(emailError = emailResult.errorMessage)
        return emailResult.successful
    }

    fun onSignUpClicked() {
            Log.d("SignUpViewModel", "onSignUpClicked: function called")
            _uiState.update { it.copy(isLoading = true, message = null) }

        viewModelScope.launch(Dispatchers.IO) {

            val isEmailValid = validateEmail()
            val isPasswordValid = validatePassword()
            val isConfirmPasswordValid = validateConfirmPassword()
            val isUserNameValid = formState.userName.isNotBlank()

            if (!isEmailValid || !isPasswordValid || !isConfirmPasswordValid || !isUserNameValid) {
                Log.d("SignUpViewModel", "onSignUpClicked: invalidation case")
            }

            try {
                Log.d("SignUpViewModel", "onSignUpClicked: _uiState value = ${_uiState.value} and uiState value = ${uiState.value}")

                val userName = formState.userName
                val email = formState.email
                val password = formState.password
                val inputParams = UserParams(userName, email, password)

                val result = signUpUserUseCase.execute(inputParams)

                when (result) {
                    is ResultState.Success<*> -> {
                        Log.d("SignUpViewModel", "onSignUpClicked: Hello from Success")
                        _uiState.value = _uiState.value.copy(isLoading = false, isSignUpSuccessful = true)
                        _uiEvent.value = SignUpUiEvent.NavigateToHome
                    }
                    is ResultState.Error<*> -> {
                        Log.d("SignUpViewModel", "onSignUpClicked: Hello from Error and ${result.message}")
                        _uiState.value = _uiState.value.copy(isLoading = false, message = result.message)
                        _uiEvent.value = SignUpUiEvent.ShowError(result.message!!)
                    }
                }

            } catch (e: Exception) {
                Log.d("SignUpViewModel", "onSignUpClicked: Hello from Catch and ${e.message}")
                _uiState.value = _uiState.value.copy(isLoading = false, message = e.message)
            }
        }
    }

    fun onImageSelectionClicked(uri: String?) {
        uri?.let {
            formState = formState.copy(profileImageUri = it)
        }
    }

}

sealed class SignUpValidationEvent {
    data class UsernameChanged(val userName: String) : SignUpValidationEvent()
    data class EmailChanged(val email: String) : SignUpValidationEvent()
    data class PasswordChanged(val password: String) : SignUpValidationEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpValidationEvent()
    data class VisiblePassword(val isVisiblePassword: Boolean) : SignUpValidationEvent()
}

sealed class SignUpUiEvent {
    data class ShowError(val message: String) : SignUpUiEvent()
    object NavigateToLogin : SignUpUiEvent()
    object NavigateToHome : SignUpUiEvent()
    object NavigateToAuth : SignUpUiEvent()
}

data class SignUpUiState (
    val isLoading: Boolean = false,
    val message: String? = null,
    val isSignUpSuccessful: Boolean = false
)

