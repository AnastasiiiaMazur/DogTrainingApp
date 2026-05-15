package com.cmps.dogtrainingapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Orange
import com.cmps.dogtrainingapp.ui.theme.White
import com.cmps.dogtrainingapp.utils.dismissKeyboard

@Composable
fun BasicButton(
    buttonText: String = "",
    onClick: () -> Unit,
    buttonColor: Color = Orange,
    textColor: Color = White,
    paddingTop: Int = 10
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        onClick = {
            dismissKeyboard(focusManager, keyboardController)
             onClick()},
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingTop.dp),
        enabled = true,
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = buttonColor
        ),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp),
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(
            text = buttonText,
            fontWeight = FontWeight.Normal,
            fontFamily = MyFontFamily,
            fontSize = 18.sp
        )
    }
}

@Composable
@Preview
fun PreviewButton() {
    BasicButton(
        buttonText = "Test",
        onClick = {}
    )
}