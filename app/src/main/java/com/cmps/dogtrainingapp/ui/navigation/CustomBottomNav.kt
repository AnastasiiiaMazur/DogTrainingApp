package com.cmps.dogtrainingapp.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.components.BottomNavIcon
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White

@Composable
fun CustomBottomNav (
    selectedTab: BottomNavItem,
    onTabSelected: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = White)
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            BottomNavIcon(
                selected = selectedTab == BottomNavItem.DASHBOARD,
                activeIcon = R.drawable.dashboard2,
                inactiveIcon = R.drawable.dashboard,
                onClick = { onTabSelected(BottomNavItem.DASHBOARD) }
            )

            BottomNavIcon(
                selected = selectedTab == BottomNavItem.TRAINING_HUB,
                activeIcon = R.drawable.training_hub2,
                inactiveIcon = R.drawable.training_hub,
                onClick = { onTabSelected(BottomNavItem.TRAINING_HUB) }
            )

            BottomNavIcon(
                selected = selectedTab == BottomNavItem.PROFILE,
                activeIcon = R.drawable.pet_profile2,
                inactiveIcon = R.drawable.pet_profile,
                onClick = { onTabSelected(BottomNavItem.PROFILE) }
            )

            BottomNavIcon(
                selected = selectedTab == BottomNavItem.HEALTH_HUB,
                activeIcon = R.drawable.health_hub2,
                inactiveIcon = R.drawable.health_hub,
                onClick = { onTabSelected(BottomNavItem.HEALTH_HUB) }
            )

            BottomNavIcon(
                selected = selectedTab == BottomNavItem.WALK_TRACKER,
                activeIcon = R.drawable.walk_tracker2,
                inactiveIcon = R.drawable.walk_tracker,
                onClick = { onTabSelected(BottomNavItem.WALK_TRACKER) }
            )
        }
    }
}


@Composable
@Preview
fun PreviewBottomNav() {
    CustomBottomNav(
        selectedTab = BottomNavItem.TRAINING_HUB,
        onTabSelected = {}
    )
}