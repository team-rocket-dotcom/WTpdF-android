package com.sahilm.wtpdf_android.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application(){
    override fun onCreate() {
        super.onCreate()
    }
}