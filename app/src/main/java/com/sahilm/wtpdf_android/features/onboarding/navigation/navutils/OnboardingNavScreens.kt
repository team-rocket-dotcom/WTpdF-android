package com.sahilm.wtpdf_android.features.onboarding.navigation.navutils

enum class OnboardingScreen{
    FIRSTONBOARDINGSCREEN,
    SECONDONBOARDINGSCREEN
}

sealed class OnboardingNavScreens(
    val route: String
) {
    object FirstOnboardingScreen: OnboardingNavScreens(OnboardingScreen.FIRSTONBOARDINGSCREEN.name)
    object SecondOnboardingScreen: OnboardingNavScreens(OnboardingScreen.SECONDONBOARDINGSCREEN.name)
}