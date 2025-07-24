package com.sahilm.wtpdf_android.features.auth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sahilm.wtpdf_android.features.auth.data.remote.GoogleSignInApiService
import com.sahilm.wtpdf_android.features.auth.data.remote.SignInApiService
import com.sahilm.wtpdf_android.features.auth.data.remote.SignUpApiService
import com.sahilm.wtpdf_android.features.auth.data.repository.google.GoogleAuthRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.google.GoogleAuthRepositoryImpl
import com.sahilm.wtpdf_android.features.auth.data.repository.signin.SignInRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signin.SignInRepositoryImpl
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.math.sign

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    @GoogleClientId
    fun provideClientId(): String = "115581519142-a9l7tbh95uf4ifhh8loetpf8u5i437u7.apps.googleusercontent.com"

    @Provides
    @Singleton
    fun providePreferenceDataStore(
        @ApplicationContext appContext: Context
    ) : DataStore<Preferences> {
        return appContext.dataStore
    }

    @Provides
    @Singleton
    fun provideSignUpRepository(
        signUpApiService: SignUpApiService
    ) : SignUpRepository {
        return SignUpRepositoryImpl(signUpApiService)
    }

    @Provides
    @Singleton
    fun provideSignInRepository(
        signInApiService: SignInApiService
    ) : SignInRepository {
        return SignInRepositoryImpl(signInApiService)
    }

    @Provides
    @Singleton
    fun provideGoogleAuthRepository(
        signInApiService: GoogleSignInApiService,
        @ApplicationContext context: Context,
        @GoogleClientId clientId: String
    ) : GoogleAuthRepository {

        return GoogleAuthRepositoryImpl(context, clientId, signInApiService)
    }

}