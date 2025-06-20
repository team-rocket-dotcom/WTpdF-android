package com.sahilm.wtpdf_android.features.auth.util

interface BaseUseCase<In, Out> {
    fun execute(input: In): Out
}