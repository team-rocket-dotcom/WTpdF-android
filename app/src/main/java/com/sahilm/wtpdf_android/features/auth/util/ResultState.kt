package com.sahilm.wtpdf_android.features.auth.util

sealed class ResultState<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : ResultState<T>(data = data)
    class Error<T>(message: String, data: T? = null) : ResultState<T>(data = data, message = message)
    class Loading<T> : ResultState<T>()
}