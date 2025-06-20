package com.sahilm.wtpdf_android.features.onboarding.ui.screen

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.components.PrimaryButton
import com.sahilm.wtpdf_android.features.auth.ui.activity.AuthActivity
import com.sahilm.wtpdf_android.features.onboarding.data.OnBoardingModel
import com.sahilm.wtpdf_android.features.onboarding.ui.activity.OnboardingActivity
import com.sahilm.wtpdf_android.ui.theme.WTpdFandroidTheme
import com.sahilm.wtpdf_android.ui.theme.backgroundLight
import com.sahilm.wtpdf_android.ui.theme.displayFontFamily
import com.sahilm.wtpdf_android.ui.theme.onBackgroundLight
import com.sahilm.wtpdf_android.ui.theme.onPrimaryDark
import com.sahilm.wtpdf_android.ui.theme.onPrimaryLight
import com.sahilm.wtpdf_android.ui.theme.onSurfaceLight
import com.sahilm.wtpdf_android.ui.theme.primaryDark
import com.sahilm.wtpdf_android.ui.theme.primaryLight
import com.sahilm.wtpdf_android.ui.theme.surfaceDimLight
import com.sahilm.wtpdf_android.ui.theme.surfaceLight
import com.sahilm.wtpdf_android.ui.theme.tertiaryDark
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun SecondOnboardingScreen(modifier: Modifier = Modifier) {
    WTpdFandroidTheme(dynamicColor = false) {
        val pages = listOf(
            OnBoardingModel(
                title = "PDF Scanner",
                description = "Scan your Docs to create PDF instantly, just out of your pocket",
                imageRes = R.drawable.first_onbaording_image
            ),
            OnBoardingModel(
                title = "AudioBook Creator",
                description = "Convert your PDFs to AudioBooks with human-like narration",
                imageRes = R.drawable.second_onboarding_image
            ),
            OnBoardingModel(
                title = "Integrated AI",
                description = "Integrate AI in your daily-life Doc utility tasks",
                imageRes = R.drawable.third_onboarding_image
            ),
            OnBoardingModel(
                title = "WTpdF",
                description = "Get started with a new age Doc utility app",
                imageRes = R.drawable.fourth_onboarding_image
            )
        )

        val pagerState = rememberPagerState(pageCount = { pages.size })
        val coroutineScope = rememberCoroutineScope()
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(backgroundLight)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) { page ->
                    SecondOnboardingItem(pages[page])
                }

                DotsIndicator(
                    dotCount = pagerState.pageCount,
                    type = ShiftIndicatorType(
                        dotsGraphic = DotGraphic(
                            color = MaterialTheme.colorScheme.onBackground,
                            size = 8.dp
                        )
                    ),
                    pagerState = pagerState
                )

                if (pagerState.currentPage != pagerState.pageCount - 1) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {

                        Text(
                            "skip", style = TextStyle(
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.clickable {
                                val skipPage = pagerState.pageCount - 1
                                coroutineScope.launch { pagerState.animateScrollToPage(skipPage) }
                            }
                        )

                        Text(
                            "Next", style = TextStyle(
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier.clickable {
                                val nextPage = pagerState.currentPage + 1
                                coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                            }
                        )
                    }
                } else {
                    val context = LocalContext.current

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(24.dp)
                    ) {
                        PrimaryButton(
                            modifier = modifier.fillMaxWidth(0.85f),
                            buttonContentText = "Get Started",
                            onClick = {
                                val intent = Intent(context, AuthActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }