package com.sahilm.wtpdf_android.features.auth.di

import com.sahilm.wtpdf_android.core.Constants
import com.sahilm.wtpdf_android.features.auth.data.remote.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideBaseUrl() : String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrlProvider: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrlProvider)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService = retrofit.create(SignUpApiService::class.java)

}