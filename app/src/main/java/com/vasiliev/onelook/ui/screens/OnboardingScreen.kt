package com.vasiliev.onelook.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

import com.vasiliev.onelook.R
import com.vasiliev.onelook.ui.theme.AppColors
import com.vasiliev.onelook.ui.theme.AppSpacing
import com.vasiliev.onelook.ui.theme.AppText

private data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {
    val pages = remember {
        listOf(
            OnboardingPage(
                imageRes = R.drawable.onboarding_1,
                title = "Keep calm and stay\nin control",
                description = "You can check your health with just one\nlook."
            ),
            OnboardingPage(
                imageRes = R.drawable.onboarding_2,
                title = "Don’t miss a single\npill, ever!",
                description = "Plan your supplementation in details."
            ),
            OnboardingPage(
                imageRes = R.drawable.onboarding_3,
                title = "Exercise more\n& breathe better",
                description = "Learn, measure, set daily goals."
            )
        )
    }

    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    Surface(color = AppColors.VioletLight) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppSpacing.ScreenPadding)
        ) {
            // Top: Skip intro (праворуч)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppSpacing.L),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Skip intro",
                    style = AppText.Body3,
                    color = AppColors.DeepBlue,
                    modifier = Modifier.clickable { onFinish() }
                )
            }

            Spacer(Modifier.height(AppSpacing.L))

            // Pager
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { pageIndex ->
                OnboardingPageContent(page = pages[pageIndex])
            }

            // Bottom (як у Figma): кнопка по центру + індикатор по центру під нею
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ArrowButton(
                    onClick = {
                        if (pagerState.currentPage == pages.lastIndex) {
                            onFinish()
                        } else {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                )

                Spacer(Modifier.height(16.dp))

                PageIndicator(
                    pageCount = pages.size,
                    currentPage = pagerState.currentPage
                )
            }
        }
    }
}

@Composable
private fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(24.dp))
                .background(AppColors.VioletLight),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(page.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .heightIn(min = 240.dp, max = 340.dp)
            )
        }

        Spacer(Modifier.height(AppSpacing.L))

        Text(
            text = page.title,
            style = AppText.H3,
            color = AppColors.DeepBlue,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppSpacing.S))

        Text(
            text = page.description,
            style = AppText.Body3,
            color = AppColors.DarkGrey,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(AppSpacing.L))
    }
}

@Composable
private fun PageIndicator(
    pageCount: Int,
    currentPage: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { index ->
            val isActive = index == currentPage
            Box(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .height(4.dp)
                    .width(if (isActive) 28.dp else 16.dp)
                    .clip(CircleShape)
                    .background(
                        if (isActive) AppColors.PurplePlum
                        else AppColors.White.copy(alpha = 0.9f)
                    )
            )
        }
    }
}

@Composable
private fun ArrowButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(AppColors.PurplePlum)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "→",
            style = AppText.Button,
            color = AppColors.White
        )
    }
}
