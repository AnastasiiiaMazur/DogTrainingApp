package com.cmps.dogtrainingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.LightGray1
import com.cmps.dogtrainingapp.ui.theme.LightGray2
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange
import com.cmps.dogtrainingapp.ui.theme.White

@Composable
fun ConfirmationDialog(
    message: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = LightGray1,
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
                paddingTop = 6,
                borderColor = Orange
            )
        },
        dismissButton = {
            BasicButton(
                buttonText = "No",
                onClick = onDismiss,
                buttonColor = LightGray1,
                textColor = Gray,
                paddingTop = 2,
                borderColor = Gray
            )
        }
    )
}