package com.cmps.dogtrainingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily

@Composable
fun ProfileInfoField(
    value: String,
    placeholder: String,
    enabled: Boolean = false,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
    onClick: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,

            focusedTextColor = Black,
            unfocusedTextColor = Black,
            disabledTextColor = DarkGray,

            focusedBorderColor = Black,
            unfocusedBorderColor = Black,
            disabledBorderColor = LightGray
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = Gray
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = MyFontFamily
        )
    )
}