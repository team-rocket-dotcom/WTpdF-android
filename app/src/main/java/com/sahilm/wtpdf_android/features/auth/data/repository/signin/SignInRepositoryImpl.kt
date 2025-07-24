package com.sahilm.wtpdf_android.features.auth.data.repository.signin

import android.util.Log
import com.sahilm.wtpdf_android.features.auth.data.model.SignInRequest
import com.sahilm.wtpdf_android.features.auth.data.model.SignInResponse
import com.sahilm.wtpdf_android.features.auth.data.remote.SignInApiService
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val signInApiService: SignInApiService
): SignInRepository {
    override suspend fun signInUser(
        email: String,
        password: String
    ): ResultState<SignInResponse?> {
        return try {

            val input = SignInRequest(
                email = email,
                password = password
            )

            val response = signInApiService.signInUser(input)
            if (response.isSuccessful && response.body() != null) {
                Log.d("SignInRepo", "signInUser: SignInRequest successful : ${response.body()}")
                ResultState.Success(response.body())
            } else {
                val error = response.errorBody().toString()
                Log.d("SignInRepo", "signInUser: SignInRequest error : $error")
                ResultState.Error(error)
            }
        } catch (e: Exception) {
            Log.d("SignInRepo", "signInUser: CatchError : ${e.message}")
            ResultState.Error("Cannot reach the serve. Please check your connection")
        }
    }
}