package com.sahilm.wtpdf_android.features.onboarding.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.components.CircularProgressBar
import com.sahilm.wtpdf_android.components.progressFlow
import com.sahilm.wtpdf_android.features.onboarding.navigation.navutils.OnboardingNavScreens
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.backgroundLight
import com.sahilm.wtpdf_android.ui.theme.primaryContainerLight
import com.sahilm.wtpdf_android.ui.theme.primaryLight
import com.sahilm.wtpdf_android.ui.theme.secondaryLight
import kotlinx.coroutines.delay

@Composable
fun FirstOnboardingScreen(
    navController: NavController
) {

    LaunchedEffect(key1 = true) {
        delay(5000)
        navController.navigate(OnboardingNavScreens.SecondOnboardingScreen.route) {
            popUpTo(OnboardingNavScreens.FirstOnboardingScreen.route) {inclusive = true}
        }
    }

    WTpdFandroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "App Icon",
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f)
                )
            CircularProgressIndicator(
                modifier = Modifier
                    .size(78.dp)
                    .rotate(0.85f),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 6.dp
            )

        }
    }
}