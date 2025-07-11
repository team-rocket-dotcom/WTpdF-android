package com.sahilm.wtpdf_android.features.auth.data.repository.signup

import android.util.Log
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
            val response = signUpApiService.signUpUser(firstName,lastName, email, password)
            if (response.isSuccessful && response.body() != null) {
                ResultState.Success(response.body())
            } else {
                val error = response.errorBody()?.toString() ?: "An unknown error occurred"
                ResultState.Error(error)
            }
        } catch (e: Exception) {
            ResultState.Error("Couldn't reach the server. please check you connection")
        }
    }
}