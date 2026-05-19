package com.cmps.dogtrainingapp.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White

@Composable
fun DailyRecItem(
    name: String,
    imageId: String
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
    ) {
        Image(
            painter = painterResource(R.drawable.rec_1),
            contentDescription = "recs image",
//            modifier = Modifier.fillMaxSize()
        )

        Text(
            text = name,
            color = White,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )
    }
}

@Composable
@Preview
fun PreviewItem() {
    DailyRecItem(
        "Dog feeding",
        "rec_1"
    )
}