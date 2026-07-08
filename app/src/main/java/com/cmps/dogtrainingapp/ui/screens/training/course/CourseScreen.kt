package com.cmps.dogtrainingapp.ui.screens.training.course

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.ui.navigation.Routes
import com.cmps.dogtrainingapp.ui.navigation.navigateHome
import com.cmps.dogtrainingapp.ui.screens.training.components.LessonCard
import com.cmps.dogtrainingapp.ui.theme.Black
import com.cmps.dogtrainingapp.ui.theme.DarkGray
import com.cmps.dogtrainingapp.ui.theme.MyFontFamily


@Composable
fun CourseRoute(
    courseId: String?,
    viewModel: CourseViewModel,
    navController: NavHostController
) {
    LaunchedEffect(courseId) {
        if (courseId != null) {
            viewModel.loadCourse(courseId)
        }
    }

    val state = viewModel.uiState

    CourseScreen(
        state = state,
        navController = navController
    )
}

@Composable
fun CourseScreen(
    state: CourseUiState,
    navController: NavHostController
) {

    val course = state.course ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 50.dp,
                bottom = 50.dp,
                start = 20.dp,
                end = 20.dp
            )
    ) {
        Text(
            text = state.course.title,
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
                .clickable{ navController.popBackStack() }
        )

        Spacer(modifier = Modifier.padding(bottom = 15.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(course.lessons) { index, lesson ->
                LessonCard(
                    lesson = lesson,
                    isCompleted = state.completedLessonIds.contains(lesson.id),
                    isUnlocked = index == 0 ||
                            state.completedLessonIds.contains(course.lessons[index - 1].id),
                    onClick = {
                        navController.navigate(
                            Routes.lesson(
                                course.id,
                                lesson.id
                            )
                        )
                    },
                    onLockedClick = {
                        // later show message
                    }
                )
            }
        }

    }



}