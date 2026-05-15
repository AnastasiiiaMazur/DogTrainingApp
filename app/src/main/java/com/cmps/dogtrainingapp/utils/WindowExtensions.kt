package com.cmps.dogtrainingapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.applyFullscreen() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        WindowInsetsControllerCompat(window, window.decorView).apply {
            hide(WindowInsetsCompat.Type.systemBars())
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN              or
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION         or
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN       or
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  or
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )
    }
}
