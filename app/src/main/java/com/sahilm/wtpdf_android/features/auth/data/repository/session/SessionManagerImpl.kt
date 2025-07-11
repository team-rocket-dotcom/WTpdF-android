package com.sahilm.wtpdf_android.features.auth.data.repository.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sahilm.wtpdf_android.features.auth.domain.session.SessionManager
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SessionManager {
    override suspend fun saveAuthToken(accessToken: String, refreshToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAccessToken(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun getRefreshToken(): String? {
        TODO("Not yet implemented")
    }

    override suspend fun clearSession() {
        TODO("Not yet implemented")
    }
}