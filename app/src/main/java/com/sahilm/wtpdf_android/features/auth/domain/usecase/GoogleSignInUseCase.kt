package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInResponse
import com.sahilm.wtpdf_android.features.auth.data.repository.google.GoogleAuthRepository
import com.sahilm.wtpdf_android.features.auth.util.ResultState

class GoogleSignInUseCase (
    private val authRepository: GoogleAuthRepository
) {
    suspend fun execute(filterAuthorizedAccounts: Boolean = false): ResultState<GoogleSignInResponse?> {
        return authRepository.signInUser(filterAuthorizedAccounts)
    }
}