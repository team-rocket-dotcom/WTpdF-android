package com.sahilm.wtpdf_android.features.auth.util

import android.util.Patterns

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isPasswordValid(password: String): Boolean {
    return password.any { it.isDigit() } && password.any {it.isLetter()}
}