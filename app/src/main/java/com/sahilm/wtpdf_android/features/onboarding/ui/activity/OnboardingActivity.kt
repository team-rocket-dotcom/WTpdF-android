package com.sahilm.wtpdf_android.features.onboarding.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.FirstOnboardingScreen
import com.sahilm.wtpdf_android.features.onboarding.ui.screen.SecondOnboardingScreen
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme

class OnboardingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WTpdFandroidTheme {
                SecondOnboardingScreen()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WTpdFandroidTheme {
        OnboardingActivity()
    }
}