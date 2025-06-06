package com.sahilm.wtpdf_android.features.onboarding.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sahilm.wtpdf_android.R
import com.sahilm.wtpdf_android.features.onboarding.data.OnBoardingModel
import com.sahilm.wtpdf_android.ui.theme.tertiaryDark
import kotlinx.coroutines.launch

@Composable
fun SecondOnboardingScreen(modifier: Modifier = Modifier) {
    val pages = listOf(
        OnBoardingModel(
            title = "Some good title",
            description = "Some good decent sized description that explains the title",
            imageRes = R.drawable.image_first_onboarding
        ),
        OnBoardingModel(
            title = "Some good title",
            description = "Some good decent sized description that explains the title",
            imageRes = R.drawable.image_first_onboarding
        ),
        OnBoardingModel(
            title = "Some good title",
            description = "Some good decent sized description that explains the title",
            imageRes = R.drawable.image_first_onboarding
        )
    )

    val pagerState = rememberPagerState(pageCount = {pages.size})
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            SecondOnboardingItem(pages[page])
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                "skip", style = TextStyle(
                    color = tertiaryDark,
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
                    color = tertiaryDark,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.clickable {
                    val nextPage = pagerState.currentPage + 1
                    coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                }
            )
        }
    }
}