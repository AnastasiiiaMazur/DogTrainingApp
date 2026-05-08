package com.cmps.dogtrainingapp.utils

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController

fun dismissKeyboard(focusManager: FocusManager, keyboardController: SoftwareKeyboardController?) {
    focusManager.clearFocus()
    keyboardController?.hide()
}

@Composable
fun Modifier.hideKeyboardOnTap(): Modifier {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    return pointerInput(Unit) {
        detectTapGestures {
            dismissKeyboard(focusManager, keyboardController)
        }
    }
}