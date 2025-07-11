package com.sahilm.wtpdf_android.features.auth.data.repository.signup

import com.sahilm.wtpdf_android.features.auth.data.model.SignUpResponse
import com.sahilm.wtpdf_android.features.auth.util.ResultState

interface SignUpRepository {
    suspend fun signUpUser(firstName: String, lastName: String, email: String, password: String) : ResultState<SignUpResponse?>
}