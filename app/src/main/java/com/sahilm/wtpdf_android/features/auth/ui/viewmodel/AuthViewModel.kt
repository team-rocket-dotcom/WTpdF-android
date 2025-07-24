package com.sahilm.wtpdf_android.features.auth.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahilm.wtpdf_android.features.auth.domain.usecase.GoogleSignInUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.GoogleSignOutUseCase
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val googleSignInUseCase: GoogleSignInUseCase,
    private val googleSignOutUseCase: GoogleSignOutUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun signInWithGoogle(filterAuthorizedAccounts: Boolean = true) {

        _uiState.value = _uiState.value.copy(isLoading = true, message = null)

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = googleSignInUseCase.execute(filterAuthorizedAccounts)) {
                is ResultState.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignUpSuccessful = true,
                        message = "Sign in successful"
                    )
                }
                is ResultState.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignUpSuccessful = false,
                        message = result.message
                    )
                }
                is ResultState.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

    fun signOut() {

        _uiState.value = _uiState.value.copy(
            isLoading = true,
            message = null
        )

        viewModelScope.launch(Dispatchers.IO) {
            when (val result = googleSignOutUseCase.execute()) {
                is ResultState.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignUpSuccessful = false,
                        message = "Signed out successfully"
                    )
                }
                is ResultState.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        message = result.message
                    )
                }
                is ResultState.Loading -> {
                    _uiState.value = _uiState.value.copy(isLoading = true)
                }
            }
        }
    }

}

data class AuthUiState (
    val isLoading: Boolean = false,
    val message: String? = null,
    val isSignUpSuccessful: Boolean = false
)