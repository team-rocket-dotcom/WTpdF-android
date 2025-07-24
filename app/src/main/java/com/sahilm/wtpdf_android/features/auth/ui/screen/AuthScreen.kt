package com.sahilm.wtpdf_android.features.auth.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.core.components.HorizontalOrDivider
import com.sahilm.wtpdf_android.core.components.PrimaryButton
import com.sahilm.wtpdf_android.core.components.SecondaryButton
import com.sahilm.wtpdf_android.core.components.TertiaryButton
import com.sahilm.wtpdf_android.features.auth.ui.viewmodel.AuthViewModel
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.displayFontFamily
import com.sahilm.wtpdf_android.ui.theme.onPrimaryLight
import com.sahilm.wtpdf_android.ui.theme.onSurfaceLight
import com.sahilm.wtpdf_android.ui.theme.onSurfaceVariantLight
import com.sahilm.wtpdf_android.ui.theme.primaryContainerLight
import com.sahilm.wtpdf_android.ui.theme.primaryLight
import com.sahilm.wtpdf_android.ui.theme.surfaceVariantLight

@Composable
fun AuthScreen(
    navigateSignUpScreen: () -> Unit,
    navigateSignInScreen: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    WTpdFandroidTheme {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.login_screen_image),
                contentDescription = "App Logo",
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Let's Go",
                fontWeight = FontWeight.Bold,
                fontFamily = displayFontFamily,
                fontSize = 24.sp,
                color = onSurfaceVariantLight
            )
            Spacer(
                modifier = Modifier.padding(8.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.90f)
            ) {
                SecondaryButton(
                    modifier = Modifier,
                    onClick = {
                        authViewModel.signInWithGoogle()
                    },
                    buttonIcon = R.drawable.icon_google_logo,
                    buttonText = "Get started with Google",
                    borderColor = primaryContainerLight,
                    contentColor = primaryLight
                )
            }
            HorizontalOrDivider()
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth(0.90f)
                    .padding(top = 16.dp),
                onClick = {navigateSignInScreen()},
                buttonContentText = "Sign In",
                containerColor = primaryLight,
                contentColor = onPrimaryLight
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Don't have an account?", color = onSurfaceVariantLight)
                TertiaryButton(
                    onClick = {navigateSignUpScreen()},
                    buttonContentText = "Sign Up",
                    buttonContentTextSize = 20.sp,
                    buttonContentTextColor = primaryLight
                )
            }
        }
    }
}