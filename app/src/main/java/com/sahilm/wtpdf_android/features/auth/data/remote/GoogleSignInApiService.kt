package com.sahilm.wtpdf_android.features.auth.data.remote

import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInRequest
import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleSignInApiService {
    @POST("api/v1/auth/google")
    suspend fun signInUser(
        @Body googleSignInRequest: GoogleSignInRequest
    ): Response<GoogleSignInResponse>
}