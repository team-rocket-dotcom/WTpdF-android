package com.sahilm.wtpdf_android.features.auth.data.remote

import com.sahilm.wtpdf_android.features.auth.data.model.SignUpResponse
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface SignUpApiService {
    @GET("api/v1/auth/register")
    suspend fun signUpUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Response<SignUpResponse>
}