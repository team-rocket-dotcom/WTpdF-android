package com.sahilm.wtpdf_android.features.auth.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.sahilm.wtpdf_android.features.auth.data.remote.SignUpApiService
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepository
import com.sahilm.wtpdf_android.features.auth.data.repository.signup.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

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

}