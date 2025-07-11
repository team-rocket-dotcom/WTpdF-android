package com.sahilm.wtpdf_android.features.auth.navigation.navmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sahilm.wtpdf_android.features.auth.navigation.navutils.AuthNavScreens
import com.sahilm.wtpdf_android.features.auth.ui.screen.AuthScreen
import com.sahilm.wtpdf_android.features.auth.ui.screen.SignInScreen
import com.sahilm.wtpdf_android.features.auth.ui.screen.SignUpScreen
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.SignUpViewModel

@Composable
fun AuthNavHostingScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = AuthNavScreens.AuthScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
       composable(route = AuthNavScreens.AuthScreen.route) {
           AuthScreen {navController.navigate(AuthNavScreens.SignUpScreen.route)}
       }
       composable(route = AuthNavScreens.SignUpScreen.route) {
           SignUpScreen()
       }
       composable(route = AuthNavScreens.LoginScreen.route) {
           SignInScreen()
       }

    }
}