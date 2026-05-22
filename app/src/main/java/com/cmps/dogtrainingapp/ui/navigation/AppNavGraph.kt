package com.cmps.dogtrainingapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventRoute
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventViewModel
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileRoute
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModel
import com.cmps.dogtrainingapp.ui.screens.walk.WalkHubRoute
import com.cmps.dogtrainingapp.ui.screens.walk.WalkViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    profileViewModel: ProfileViewModel,
    walkViewModel: WalkViewModel,
    healthViewModel: HealthEventViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ) {
        composable(Routes.DASHBOARD) {

        }

        composable(Routes.TRAINING_HUB) {

        }

        composable(Routes.PROFILE) {
            ProfileRoute(profileViewModel)
        }

        composable(Routes.HEALTH_HUB) {
            HealthEventRoute(healthViewModel)
        }

        composable(Routes.WALK_TRACKER) {
            WalkHubRoute(walkViewModel)
        }
    }
}
