package com.sahilm.wtpdf_android.features.auth.domain.usecase

import android.util.Log
import com.sahilm.wtpdf_android.features.auth.data.model.SignUpResponse
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepository
import com.sahilm.wtpdf_android.features.auth.domain.model.AuthUserDetails
import com.sahilm.wtpdf_android.features.auth.domain.model.UserData
import com.sahilm.wtpdf_android.features.auth.util.BaseUseCase
import com.sahilm.wtpdf_android.features.auth.util.ResultState
import com.sahilm.wtpdf_android.features.auth.util.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import kotlin.math.sign

class SignUpUserUseCase @Inject constructor(
    private val signUpRepository: SignUpRepository
) : BaseUseCase<UserParams, Any> {
    override fun execute(input: UserParams): Any {
        val firstSpaceIndex = input.userName.trim().indexOf(' ')
        val firstName = input.userName.trim().substring(0, firstSpaceIndex)
        val lastName = input.userName.trim().substring(firstSpaceIndex + 1)

        val signUpResponse = runBlocking(Dispatchers.IO) {
            signUpRepository.signUpUser(
                firstName = firstName,
                lastName = lastName,
                email = input.email,
                password = input.password
            )
        }

        if (signUpResponse is ResultState.Success) {
            val signUpResult = signUpResponse.data

            // save the signUpResult.accessToken and signUpResult.refreshToken to the sessionManager
            Log.d("SignUpUserUseCase", "execute: $signUpResult")

            val userDetails = signUpResult?.user?.toModel()

            return ResultState.Success(signUpResult)
        } else {
            return ResultState.Error(signUpResponse.message ?: "Sign up failed", signUpResponse.data)
        }
    }
}

data class UserParams(
    val userName: String,
    val email: String,
    val password: String
)