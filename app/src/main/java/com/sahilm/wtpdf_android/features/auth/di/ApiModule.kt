package com.sahilm.wtpdf_android.features.auth.di

import com.sahilm.wtpdf_android.core.Constants
import com.sahilm.wtpdf_android.features.auth.data.remote.GoogleSignInApiService
import com.sahilm.wtpdf_android.features.auth.data.remote.SignInApiService
import com.sahilm.wtpdf_android.features.auth.data.remote.SignUpApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl() : String = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(
        @BaseUrl baseUrlProvider: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrlProvider)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideSignUpApiService(retrofit: Retrofit): SignUpApiService = retrofit.create(SignUpApiService::class.java)

    @Provides
    @Singleton
    fun provideSignInApiService(retrofit: Retrofit): SignInApiService = retrofit.create(SignInApiService::class.java)

    @Provides
    @Singleton
    fun provideGoogleSignInApiService(retrofit: Retrofit): GoogleSignInApiService = retrofit.create(
        GoogleSignInApiService::class.java)

}