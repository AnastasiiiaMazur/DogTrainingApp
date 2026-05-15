package com.cmps.dogtrainingapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavIcon(
    selected: Boolean,
    activeIcon: Int,
    inactiveIcon: Int,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(
            id = if (selected) activeIcon else inactiveIcon
        ),
        contentDescription = "Navigation icon",
        modifier = Modifier
            .size(50.dp)
            .clickable { onClick() }
    )
}