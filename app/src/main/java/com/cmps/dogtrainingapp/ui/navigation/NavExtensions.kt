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

fun NavHostController.navigateAllEvents() {
    navigate(Routes.ALL_EVENTS) {
        popUpTo(Routes.ALL_EVENTS) {
            inclusive = false
        }
        launchSingleTop = true
    }
}

fun NavHostController.navigateProfile() {
    navigate(Routes.PROFILE) {
        popUpTo(Routes.PROFILE) {
            inclusive = false
        }
        launchSingleTop = true
    }
}