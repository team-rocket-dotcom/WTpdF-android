package com.sahilm.wtpdf_android.features.auth.data.repository.google

import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInResponse
import com.sahilm.wtpdf_android.features.auth.util.ResultState

interface GoogleAuthRepository {
    suspend fun signInUser(filterByAuthorizedAccounts: Boolean = true): ResultState<GoogleSignInResponse?>
    suspend fun signOutUser(): ResultState<Unit>
    suspend fun getCurrentUser(): String
}