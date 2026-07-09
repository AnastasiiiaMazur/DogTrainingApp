package com.cmps.dogtrainingapp.ui.screens.training

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cmps.dogtrainingapp.ui.screens.training.components.CourseCard
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.model.Course
import com.cmps.dogtrainingapp.data.model.CourseLevel
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily


@Composable
fun TrainingRoute(
    viewModel: TrainingViewModel,
    navController: NavHostController
) {
    val state = viewModel.uiState

    TrainingScreen(
        state = state,
        navController = navController
    )
}

@Composable
fun TrainingScreen(
    state: TrainingUiState,
    navController: NavHostController
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = 50.dp,
            bottom = 50.dp,
            start = 20.dp,
            end = 20.dp
        )
    ) {

        item {
            Text(
                text = "Training Hub",
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
                    .clickable{ navController.navigateHome() }
            )

            Spacer(modifier = Modifier.padding(bottom = 15.dp))
        }

        items(state.courses) { course ->

            CourseCard(
                course = course,
                completedLessons = state.completedLessonsByCourse[course.id] ?: 0,
                onClick = {
                    navController.navigate(Routes.course(course.id))
                }
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrainingScreenPreview() {

    val state = TrainingUiState(
        courses = listOf(
            Course(
                id = "1",
                title = "Essential Commands",
                description = "Teach fundamental commands every puppy needs.",
                level = CourseLevel.INTERMEDIATE,
                imageName = "mod1_l1",
                lessons = emptyList()
            ),
            Course(
                id = "2",
                title = "Potty Training",
                description = "Quick methods to house-train your puppy.",
                level = CourseLevel.INTERMEDIATE,
                imageName = "mod2_l1",
                lessons = emptyList()
            )
        )
    )

    TrainingScreen(
        state = state,
        navController = rememberNavController()
    )
}