package com.sahilm.wtpdf_android.features.auth.data.repository.signin

import com.sahilm.wtpdf_android.features.auth.data.model.SignInResponse
import com.sahilm.wtpdf_android.features.auth.util.ResultState

interface SignInRepository {
    suspend fun signInUser(email: String, password: String) : ResultState<SignInResponse?>
}