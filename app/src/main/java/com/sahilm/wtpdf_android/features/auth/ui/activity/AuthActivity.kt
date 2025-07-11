package com.sahilm.wtpdf_android.features.auth.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.compose.rememberNavController
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.auth.navigation.navmap.AuthNavHostingScreen
import com.sahilm.wtpdf_android.features.auth.navigation.navutils.AuthNavScreens
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WTpdFandroidTheme {
                Surface {
                    AuthNavHostingScreen(
                        navController = rememberNavController(),
                    )
                }
            }
        }
    }
}