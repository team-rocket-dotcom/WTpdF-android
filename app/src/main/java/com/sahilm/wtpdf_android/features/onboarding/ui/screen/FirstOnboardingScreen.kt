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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.components.CircularProgressBar
import com.sahilm.wtpdf_android.components.progressFlow
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.backgroundLight
import com.sahilm.wtpdf_android.ui.theme.primaryContainerLight
import com.sahilm.wtpdf_android.ui.theme.primaryLight
import com.sahilm.wtpdf_android.ui.theme.secondaryLight
import kotlinx.coroutines.delay

@Composable
fun FirstOnboardingScreen() {
    WTpdFandroidTheme {
        val progressFlow = remember { progressFlow(delayTime = 10L) }
        val progressState = progressFlow.collectAsState(initial = 0f)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundLight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
                Image(
                    painter = painterResource(R.mipmap.ic_launcher_foreground),
                    contentDescription = "App Icon",
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f)
                )
                CircularProgressBar(
                    progress = 0.85f,
                    startAngle = 0f,
                    size = 72.dp,
                    animationOn = true,
                    repeatOn = true
                )

        }
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnboardingScreenPreview() {
    WTpdFandroidTheme {
        FirstOnboardingScreen()
    }
}