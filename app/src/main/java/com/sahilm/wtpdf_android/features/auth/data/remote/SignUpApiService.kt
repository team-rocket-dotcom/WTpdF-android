package com.sahilm.wtpdf_android.features.auth.data.remote

import com.sahilm.wtpdf_android.features.auth.data.model.SignUpRequest
import com.sahilm.wtpdf_android.features.auth.data.model.SignUpResponse
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignUpApiService {
    @POST("api/v1/auth/register")
    suspend fun signUpUser(
        @Body request: SignUpRequest
    ): Response<SignUpResponse>
}