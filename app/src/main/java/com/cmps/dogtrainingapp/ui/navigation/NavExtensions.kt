package com.cmps.dogtrainingapp.ui.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateHome() {
    navigate(Routes.DASHBOARD) {
        popUpTo(Routes.DASHBOARD) {
            inclusive = false
        }
        launchSingleTop = true
    }
}