package com.cmps.dogtrainingapp.ui.screens.dashboard.recommendation

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.components.BasicButton
import com.cmps.dogtrainingapp.ui.screens.training.components.LessonItem
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.Red

@Composable
fun RecommendationRoute(
    recId: String?,
    viewModel: RecViewModel,
    navController: NavHostController
) {

    LaunchedEffect(recId) {
        if (recId != null) {
            viewModel.loadRec(recId)
        }
    }

    val state = viewModel.uiState

    RecommendationScreen(
        state = state,
        navController = navController
    )
}

@Composable
fun RecommendationScreen(
    state: RecUiState,
    navController: NavHostController
) {

    val context = LocalContext.current
    val rec = state.rec ?: return

    val imageRes = context.resources.getIdentifier(
        rec.imageName,
        "drawable",
        context.packageName
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 30.dp,
                bottom = 10.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {

        Column(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Daily Recommendation",
                color = Black,
                fontSize = 26.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Bold
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.back_button),
                contentDescription = "Back button",
                tint = DarkGray,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(25.dp)
                    .clickable { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "rec image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = rec.title,
                color = Black,
                fontSize = 20.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.SemiBold)
                    ) { append("Goal: ") }
                    append(rec.goal)
                },
                color = Black,
                fontSize = 18.sp,
                fontFamily = MyFontFamily
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Tips:",
                color = Black,
                fontSize = 18.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(10.dp))

            rec.tips.forEachIndexed { index, tip ->

                Text(
                    text = "${index + 1}. $tip",
                    color = Black,
                    fontSize = 16.sp,
                    fontFamily = MyFontFamily,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}