package com.cmps.dogtrainingapp.ui.screens.training.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily

@Composable
fun LessonItem(
    type: String,
    item: String,
    itemNum: Int
) {
    Column{
        Text(
            text = "$type $itemNum:",
            color = Black,
            fontSize = 17.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = item,
            color = Black,
            fontSize = 15.sp,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.Normal
        )
    }
}