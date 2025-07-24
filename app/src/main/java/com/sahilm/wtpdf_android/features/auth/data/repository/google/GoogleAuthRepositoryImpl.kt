package com.sahilm.wtpdf_android.features.auth.data.repository.google

import android.content.Context
import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInRequest
import com.sahilm.wtpdf_android.features.auth.data.model.GoogleSignInResponse
import com.sahilm.wtpdf_android.features.auth.data.remote.GoogleSignInApiService
import com.sahilm.wtpdf_android.features.auth.data.utils.GoogleSignInManager
import com.sahilm.wtpdf_android.features.auth.di.GoogleClientId
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GoogleAuthRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    @GoogleClientId private val clientId: String,
    private val googleSignInApiService: GoogleSignInApiService
) : GoogleAuthRepository {
    override suspend fun signInUser(filterByAuthorizedAccounts: Boolean): ResultState<GoogleSignInResponse?> {
        return try {
            var request: GoogleSignInRequest? = null
            val token = GoogleSignInManager.googleSignIn(context, clientId, true)
            if (token != null) {
                request = GoogleSignInRequest(token)
            }
            if (request != null) {
                val response = googleSignInApiService.signInUser(request)
                if (response.isSuccessful && response.body() != null) {
                    ResultState.Success(response.body())
                } else {
                    val error = response.errorBody()?.toString() ?: "An unknown error occurred"
                    ResultState.Error(error)
                }
            } else {
                ResultState.Error("Check your connection")
            }
        } catch (e: Exception) {
            ResultState.Error(e.message ?: "Could not reach server")
        }
    }

    override suspend fun signOutUser(): ResultState<Unit> {
        return try {
            GoogleSignInManager.signOut(context)
            ResultState.Success(Unit)
        } catch (e: Exception) {
            ResultState.Error(e.message.toString())
        }
    }

    override suspend fun getCurrentUser(): String {
        TODO("Not yet implemented")
    }

}