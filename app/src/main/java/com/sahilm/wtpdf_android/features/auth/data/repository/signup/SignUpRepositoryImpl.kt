package com.sahilm.wtpdf_android.features.auth.data.repository.signup

import android.util.Log
import com.sahilm.wtpdf_android.features.auth.data.model.SignUpRequest
import com.sahilm.wtpdf_android.features.auth.data.model.SignUpResponse
import com.sahilm.wtpdf_android.features.auth.data.remote.SignUpApiService
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import javax.inject.Inject
import kotlin.math.sign

class SignUpRepositoryImpl @Inject constructor(
    private val signUpApiService: SignUpApiService
) : SignUpRepository{
    override suspend fun signUpUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) : ResultState<SignUpResponse?> {
        return try {
            val request = SignUpRequest(
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName
            )
            val response = signUpApiService.signUpUser(request)
            if (response.isSuccessful && response.body() != null) {
                ResultState.Success(response.body())
            } else {
                val error = response.errorBody()?.toString() ?: "An unknown error occurred"
                ResultState.Error(error)
            }
        } catch (e: Exception) {
            Log.d("SignUpRepo", "signUpUser: ${e.message}")
            ResultState.Error("Couldn't reach the server. please check you connection")
        }
    }
}