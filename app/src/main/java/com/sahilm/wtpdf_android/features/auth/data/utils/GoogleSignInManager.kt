package com.sahilm.wtpdf_android.features.auth.data.utils

import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.NoCredentialException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

object GoogleSignInManager {

    private lateinit var credentialManager: CredentialManager

    suspend fun googleSignIn (
        context: Context,
        clientId: String,
        filterByAuthorizedAccounts: Boolean
    ): String? {
        if (::credentialManager.isInitialized.not()) {
            credentialManager = CredentialManager.create(context)
        }

        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption
            .Builder()
            .setFilterByAuthorizedAccounts(filterByAuthorizedAccounts)
            .setServerClientId(clientId)
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest = GetCredentialRequest
            .Builder()
            .addCredentialOption(googleIdOption)
            .build()

        return requestSignIn(
            context,
            request,
            filterByAuthorizedAccounts,
            clientId
        )
    }

    private suspend fun requestSignIn (
        context: Context,
         request: GetCredentialRequest,
        filterByAuthorizedAccounts: Boolean,
        clientId: String
    ) : String? {

        try {
            val result = credentialManager.getCredential(
                request = request,
                context = context
            )
            return handleCredentials(result.credential)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            if (e is NoCredentialException && filterByAuthorizedAccounts) {
                return googleSignIn(
                    context,
                    clientId,
                    false,
                )
            }
        }
        return null
    }

    private fun handleCredentials(credential: Credential) : String? {
        when (credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        return googleIdTokenCredential.idToken
                    } catch (e: GoogleIdTokenParsingException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        return null
    }

    suspend fun signOut(context: Context) {
        if (::credentialManager.isInitialized.not()) {
            credentialManager = CredentialManager.create(context)
        }
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}