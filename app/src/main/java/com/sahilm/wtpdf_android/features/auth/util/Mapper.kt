package com.sahilm.wtpdf_android.features.auth.util

interface Mapper<in From, out To> {
    fun map(from: From): To
}

