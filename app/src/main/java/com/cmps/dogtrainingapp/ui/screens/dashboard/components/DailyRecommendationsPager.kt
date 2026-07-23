package com.cmps.dogtrainingapp.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.data.model.recs.Recommendation
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Orange

@Composable
fun DailyRecommendationsPager(
    recommendations: List<Recommendation>,
    onClick: (Recommendation) -> Unit
) {
    val pages = recommendations.chunked(2)

    val pagerState = rememberPagerState(
        pageCount = { pages.size }
    )

    Column {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth()
        ) { page ->

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {

                pages[page].forEach { recommendation ->

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {
                        DailyRecItem(
                            recommendation = recommendation,
                            onClick = {
                                onClick(recommendation)
                            }
                        )
                    }
                }

                if (pages[page].size == 1) {
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {

            repeat(pages.size) { index ->

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(
                            if (pagerState.currentPage == index) 9.dp
                            else 7.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (pagerState.currentPage == index)
                                Orange
                            else
                                LightGray
                        )
                )
            }
        }
    }
}