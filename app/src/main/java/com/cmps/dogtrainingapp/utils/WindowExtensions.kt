package com.cmps.dogtrainingapp.utils

import android.app.Activity
import android.os.Build
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlin.math.max

//fun Activity.applyFullscreen() {
//    WindowCompat.setDecorFitsSystemWindows(window, false)
//    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        WindowInsetsControllerCompat(window, window.decorView).apply {
//            hide(WindowInsetsCompat.Type.systemBars())
//            systemBarsBehavior =
//                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//        }
//    } else {
//        @Suppress("DEPRECATION")
//        window.decorView.systemUiVisibility = (
//                View.SYSTEM_UI_FLAG_FULLSCREEN              or
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION         or
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN       or
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION  or
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                )
//    }
//
//    ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
//        val sys = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//        val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
//        val bottom = max(sys.bottom, ime.bottom)
//        v.setPadding(sys.left, sys.top, sys.right, bottom)
//        insets
//    }
//}

fun Activity.applyFullscreen() {
    val decorView = window.decorView

    // Remove any padding or listener left by previous inset handling
    ViewCompat.setOnApplyWindowInsetsListener(decorView, null)
    decorView.setPadding(0, 0, 0, 0)

    WindowCompat.setDecorFitsSystemWindows(window, false)

    WindowCompat.getInsetsController(window, decorView).apply {
        hide(
            WindowInsetsCompat.Type.statusBars() or
                    WindowInsetsCompat.Type.navigationBars()
        )

        systemBarsBehavior =
            WindowInsetsControllerCompat
                .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    ViewCompat.requestApplyInsets(decorView)
}
