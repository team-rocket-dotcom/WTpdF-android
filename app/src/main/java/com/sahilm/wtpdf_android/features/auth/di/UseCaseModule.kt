package com.sahilm.wtpdf_android.features.auth.di

import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepositoryImpl
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



}