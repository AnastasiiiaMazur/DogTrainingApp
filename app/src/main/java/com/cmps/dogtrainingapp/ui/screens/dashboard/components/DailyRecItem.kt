package com.cmps.dogtrainingapp.ui.screens.dashboard.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.recs.Recommendation
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White

@Composable
fun DailyRecItem(
    recommendation: Recommendation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val imageRes = context.resources.getIdentifier(
        recommendation.imageName,
        "drawable",
        context.packageName
    )

    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(15.dp))
            .height(130.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "course image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = 0.25f))
        )

        Text(
            text = recommendation.title,
            color = White,
            fontFamily = MyFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 15.dp)
                .align(Alignment.BottomStart)

        )
    }
}

@Composable
@Preview
fun PreviewItem() {
    DailyRecItem(
        recommendation = Recommendation(
            id = "01",
            title = "Healthy Feeding",
            imageName = "rec_2",
            goal = "",
            tips = emptyList(),
            dailyAction = "",
        ),
        onClick = {}
    )
}