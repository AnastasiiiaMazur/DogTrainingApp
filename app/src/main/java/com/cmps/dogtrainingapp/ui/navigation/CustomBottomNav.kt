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
import androidx.navigation.NavHostController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.components.BottomNavIcon
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.Red
import com.cmps.dogtrainingapp.ui.theme.White

@Composable
fun CustomBottomNav (
    currentRoute: String?,
    navController: NavHostController
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
                selected = currentRoute == Routes.DASHBOARD,
                activeIcon = R.drawable.dashboard2,
                inactiveIcon = R.drawable.dashboard,
                onClick = { navController.navigate(Routes.DASHBOARD) }
            )

            BottomNavIcon(
                selected = currentRoute == Routes.TRAINING_HUB,
                activeIcon = R.drawable.training_hub2,
                inactiveIcon = R.drawable.training_hub,
                onClick = { navController.navigate(Routes.TRAINING_HUB) }
            )

            BottomNavIcon(
                selected = currentRoute == Routes.PROFILE,
                activeIcon = R.drawable.pet_profile2,
                inactiveIcon = R.drawable.pet_profile,
                onClick = { navController.navigate(Routes.PROFILE) }
            )

            BottomNavIcon(
                selected = currentRoute == Routes.HEALTH_HUB,
                activeIcon = R.drawable.health_hub2,
                inactiveIcon = R.drawable.health_hub,
                onClick = { navController.navigate(Routes.HEALTH_HUB) }
            )

            BottomNavIcon(
                selected = currentRoute == Routes.WALK_TRACKER,
                activeIcon = R.drawable.walk_tracker2,
                inactiveIcon = R.drawable.walk_tracker,
                onClick = { navController.navigate(Routes.WALK_TRACKER) }
            )
        }
    }
}