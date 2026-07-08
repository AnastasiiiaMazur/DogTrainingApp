package com.cmps.dogtrainingapp.ui.screens.training.course

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import com.cmps.dogtrainingapp.ui.screens.training.components.LessonCard


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

    // in here use lesson cards

    val course = state.course ?: return

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(course.lessons) { index, lesson ->
            LessonCard(
                lesson = lesson,
                lessonNumber = index + 1,
                isCompleted = state.completedLessonIds.contains(lesson.id),
                isUnlocked = index == 0 ||
                        state.completedLessonIds.contains(course.lessons[index - 1].id),
                onClick = {
                    // later navigate to lesson screen
                },
                onLockedClick = {
                    // later show message
                }
            )
        }
    }

}