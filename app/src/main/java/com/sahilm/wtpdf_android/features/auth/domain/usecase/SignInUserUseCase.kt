package com.sahilm.wtpdf_android.features.auth.domain.usecase

import com.sahilm.wtpdf_android.features.auth.data.repository.signin.SignInRepository
import com.sahilm.wtpdf_android.features.auth.util.BaseUseCase
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SignInUserUseCase @Inject constructor(
    private val signInRepository: SignInRepository
) : BaseUseCase<SignInUserParams, Any> {
    override fun execute(input: SignInUserParams): Any {
        val signInResponse = runBlocking(Dispatchers.IO) {
            signInRepository.signInUser(input.email, input.password)
        }

        if (signInResponse is ResultState.Success) {
            val signUpResult = signInResponse.data

            // save the signInResult.accessToken and the signInResult.refreshToken to the SessionManager

            return ResultState.Success(signUpResult)
        } else {
            return ResultState.Error(signInResponse.message ?: "Sign In Failed", signInResponse.data)
        }
    }
}

data class SignInUserParams (
    val email : String,
    val password : String
)