package com.sahilm.wtpdf_android.features.auth.di

import com.sahilm.wtpdf_android.features.auth.data.repository.google.GoogleAuthRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signin.SignInRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepositoryImpl
import com.sahilm.wtpdf_android.features.auth.domain.usecase.GoogleSignInUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.GoogleSignOutUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignInUserUseCase
import com.sahilm.wtpdf_android.features.auth.domain.usecase.SignUpUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSignUpUseCase (
        signUpRepository: SignUpRepository
    ): SignUpUserUseCase {
        return SignUpUserUseCase(signUpRepository)
    }

    @Provides
    fun provideSignInUseCase (
        signInRepository: SignInRepository
    ) : SignInUserUseCase {
        return SignInUserUseCase(signInRepository)
    }

    @Provides
    fun provideGoogleSignInUseCase(
        googleAuthRepository: GoogleAuthRepository
    ) : GoogleSignInUseCase {
        return GoogleSignInUseCase(googleAuthRepository)
    }

    @Provides
    fun provideGoogleSignOutUseCase(
        googleAuthRepository: GoogleAuthRepository
    ) : GoogleSignOutUseCase {
        return GoogleSignOutUseCase(googleAuthRepository)
    }



}