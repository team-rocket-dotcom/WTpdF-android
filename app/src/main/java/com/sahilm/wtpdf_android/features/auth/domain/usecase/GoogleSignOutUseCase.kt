package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.features.auth.data.repository.google.GoogleAuthRepository
import com.sahilm.wtpdf_android.features.auth.util.ResultState

class GoogleSignOutUseCase(
    private val authRepository: GoogleAuthRepository
) {
    suspend fun execute() : ResultState<Unit> {
        return authRepository.signOutUser()
    }
}