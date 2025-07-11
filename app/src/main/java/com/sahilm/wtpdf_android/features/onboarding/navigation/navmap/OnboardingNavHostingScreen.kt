package com.sahilm.wtpdf_android.features.onboarding.navigation.navmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sahilm.wtpdf_android.features.onboarding.navigation.navutils.OnboardingNavScreens
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.FirstOnboardingScreen
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.SecondOnboardingScreen

@Composable
fun OnboardingNavHostingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = OnboardingNavScreens.FirstOnboardingScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = OnboardingNavScreens.FirstOnboardingScreen.route) {
            FirstOnboardingScreen { navController.navigate(OnboardingNavScreens.SecondOnboardingScreen.route) }
        }
        composable(route = OnboardingNavScreens.SecondOnboardingScreen.route) {
            SecondOnboardingScreen()
        }
    }
}