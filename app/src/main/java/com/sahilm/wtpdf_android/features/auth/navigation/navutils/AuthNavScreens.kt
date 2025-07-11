package com.sahilm.wtpdf_android.features.auth.navigation.navutils

import com.sahilm.wtpdf_android.features.auth.navigation.navutils.AuthScreen.*

enum class AuthScreen {
    AUTHSCREEN,
    SIGNUPSCREEN,
    LOGINSCREEN
}

sealed class AuthNavScreens(
    val route : String
) {
    object AuthScreen : AuthNavScreens(AUTHSCREEN.name)
    object SignUpScreen : AuthNavScreens(SIGNUPSCREEN.name)
    object LoginScreen : AuthNavScreens(LOGINSCREEN.name)
}