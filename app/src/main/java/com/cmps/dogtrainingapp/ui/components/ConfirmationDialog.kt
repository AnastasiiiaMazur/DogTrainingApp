package com.cmps.dogtrainingapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily

@Composable
fun ConfirmationDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Text(
                text = message,
                textAlign = TextAlign.Center,
                color = Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MyFontFamily)
        },
        confirmButton = {
            BasicButton(
                buttonText = "Yes",
                onClick = onConfirm,
                paddingTop = 6
            )
        },
        dismissButton = {
            BasicButton(
                buttonText = "No",
                onClick = onDismiss,
                buttonColor = Gray,
                paddingTop = 2
            )
        }
    )
}