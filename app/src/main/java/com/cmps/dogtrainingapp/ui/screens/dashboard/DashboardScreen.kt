package com.cmps.dogtrainingapp.ui.screens.dashboard

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateProfile
import com.cmps.dogtrainingapp.ui.screens.dashboard.components.DailyRecItem
import com.cmps.dogtrainingapp.ui.screens.dashboard.components.DailyRecommendationsPager
import com.cmps.dogtrainingapp.ui.screens.health.components.EventStatus
import com.cmps.dogtrainingapp.ui.screens.health.components.HealthEventCard
import com.cmps.dogtrainingapp.ui.screens.training.components.CourseCard
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.Gray
import com.cmps.dogtrainingapp.ui.theme.LightGray1
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily
import com.cmps.dogtrainingapp.ui.theme.White
import java.io.File


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel,
    navController: NavHostController
) {

    val state = viewModel.uiState

    DashboardScreen(
        state = state,
        navController = navController,
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    state: DashboardUiState,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, top = 50.dp, end = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row (
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(White)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            val imageModel: Any = when {
                !state.currentPet?.imageUri.isNullOrBlank() &&
                        File(state.currentPet?.imageUri!!).exists() -> {
                    Uri.fromFile(File(state.currentPet!!.imageUri!!))
                }

                else -> {
                    R.drawable.dog_default
                }
            }

            AsyncImage(
                model = imageModel,
                contentDescription = "Pet image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(45.dp)
                    .clip(CircleShape)
            )

            Text(
                text = state.currentPet?.name ?: "Your Pet",
                color = Black,
                fontSize = 22.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.settings_button),
                contentDescription = "completion mark",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clickable { navController.navigateProfile() }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Upcoming Events",
            fontWeight = FontWeight.SemiBold,
            fontFamily = MyFontFamily,
            fontSize = 20.sp
        )

        if (state.upcomingEvents.isEmpty()) {

            Text(
                text = "You don't have any events this week.",
                color = Gray,
                fontSize = 15.sp,
                fontFamily = MyFontFamily,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(vertical = 5.dp)
            )

        } else {

            state.upcomingEvents.forEach { event ->

                HealthEventCard(
                    event = event,
                    onEditClicked = {},
                    onCompleteClicked = {},
                    dashboard = true,
                    navClick = { navController.navigate(Routes.HEALTH_HUB) }
                )

                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Today's Recommendations",
            fontWeight = FontWeight.SemiBold,
            fontFamily = MyFontFamily,
            fontSize = 20.sp
        )

        DailyRecommendationsPager(
            recommendations = state.dailyRecs,
            onClick = { recommendation ->
                navController.navigate(Routes.recommendation(recommendation.id))
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Current Training Progress",
            fontWeight = FontWeight.SemiBold,
            fontFamily = MyFontFamily,
            fontSize = 20.sp
        )

        state.courseProgress?.let { course ->
            CourseCard(
                course = course,
                completedLessons = state.completedLessons,
                onClick = { navController.navigate(Routes.course(course.id)) }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun Preview() {
    DashboardScreen(
        state = DashboardUiState(

        ),
        navController = rememberNavController()
    )
}