package com.sahilm.wtpdf_android.features.onboarding.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.sahilm.wtpdf_android.features.onboarding.navigation.navmap.OnboardingNavHostingScreen
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.FirstOnboardingScreen
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.SecondOnboardingScreen
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WTpdFandroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OnboardingNavHostingScreen(
                        modifier = Modifier,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}


