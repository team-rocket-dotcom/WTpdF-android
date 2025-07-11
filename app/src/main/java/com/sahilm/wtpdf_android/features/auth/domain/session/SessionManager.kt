package com.sahilm.wtpdf_android.features.auth.domain.session

interface SessionManager {
    suspend fun saveAuthToken(accessToken: String, refreshToken: String)
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun clearSession()
}