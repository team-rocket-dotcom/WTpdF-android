package com.sahilm.wtpdf_android.features.auth.data.remote

import com.sahilm.wtpdf_android.features.auth.data.model.SignInRequest
import com.sahilm.wtpdf_android.features.auth.data.model.SignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignInApiService {
    @POST("api/v1/auth/login")
    suspend fun signInUser(
        @Body signInRequest: SignInRequest
    ) : Response<SignInResponse>
}