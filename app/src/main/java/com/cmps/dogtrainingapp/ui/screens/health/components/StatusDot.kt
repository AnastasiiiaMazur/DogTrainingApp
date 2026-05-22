package com.cmps.dogtrainingapp.ui.screens.health.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.Green
import com.cmps.dogtrainingapp.ui.theme.LightGray2
import com.cmps.dogtrainingapp.ui.theme.LightGreen
import com.cmps.dogtrainingapp.ui.theme.LightRed
import com.cmps.dogtrainingapp.ui.theme.Red

@Composable
fun StatusDot(
    status: EventStatus,
    modifier: Modifier = Modifier
) {
    val colors = when (status) {
        EventStatus.OVERDUE -> listOf(Red, LightRed)
        EventStatus.UPCOMING -> listOf(Green, LightGreen)
        EventStatus.COMPLETED -> listOf(Gray, LightGray2)
    }

    Box(
        modifier = modifier
            .size(15.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(colors)
            )
    )
}