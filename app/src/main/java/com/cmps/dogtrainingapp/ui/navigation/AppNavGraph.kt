package com.cmps.dogtrainingapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cmps.dogtrainingapp.ui.screens.dashboard.DashboardRoute
import com.cmps.dogtrainingapp.ui.screens.dashboard.DashboardViewModel
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventRoute
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventViewModel
import com.cmps.dogtrainingapp.ui.screens.health.addEditEvent.AddEditEventRoute
import com.cmps.dogtrainingapp.ui.screens.health.addEditEvent.AddEditViewModel
import com.cmps.dogtrainingapp.ui.screens.health.allevents.ViewAllHealthEventsRoute
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileRoute
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModel
import com.cmps.dogtrainingapp.ui.screens.training.TrainingRoute
import com.cmps.dogtrainingapp.ui.screens.training.TrainingViewModel
import com.cmps.dogtrainingapp.ui.screens.training.course.CourseRoute
import com.cmps.dogtrainingapp.ui.screens.training.course.CourseViewModel
import com.cmps.dogtrainingapp.ui.screens.training.lesson.LessonRoute
import com.cmps.dogtrainingapp.ui.screens.training.lesson.LessonViewModel
import com.cmps.dogtrainingapp.ui.screens.walk.WalkHubRoute
import com.cmps.dogtrainingapp.ui.screens.walk.WalkViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(
    navController: NavHostController,
    dashboardViewModel: DashboardViewModel,
    profileViewModel: ProfileViewModel,
    walkViewModel: WalkViewModel,
    healthViewModel: HealthEventViewModel,
    trainingViewModel: TrainingViewModel,
    courseViewModel: CourseViewModel,
    lessonViewModel: LessonViewModel,
    addEditViewModel: AddEditViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.DASHBOARD
    ) {
        composable(Routes.DASHBOARD) {
            DashboardRoute(dashboardViewModel, navController)
        }

        composable(Routes.TRAINING_HUB) {
            TrainingRoute(trainingViewModel, navController)
        }

        composable(Routes.PROFILE) {
            ProfileRoute(profileViewModel, navController)
        }

        composable(Routes.HEALTH_HUB) {
            HealthEventRoute(healthViewModel, navController)
        }

        composable(Routes.WALK_TRACKER) {
            WalkHubRoute(walkViewModel, navController)
        }

        composable(Routes.COURSE) { backStackEntry ->

            val courseId = backStackEntry.arguments?.getString("courseId")

            CourseRoute(
                courseId = courseId,
                viewModel = courseViewModel,
                navController = navController
            )
        }

        composable(Routes.LESSON) { backStackEntry ->

            val courseId = backStackEntry.arguments?.getString("courseId")
            val lessonId = backStackEntry.arguments?.getString("lessonId")

            LessonRoute(
                courseId = courseId,
                lessonId = lessonId,
                viewModel = lessonViewModel,
                navController = navController
            )
        }

        composable(
            route = Routes.ADD_EDIT_EVENT,
            arguments = listOf(
                navArgument("eventId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->

            val argument = backStackEntry.arguments
                ?.getLong("eventId")
                ?: -1L

            val eventId = argument.takeIf { it != -1L }

            AddEditEventRoute(
                eventId = eventId,
                navController = navController,
                viewModel = addEditViewModel
            )
        }

        composable(Routes.ALL_EVENTS) {
            ViewAllHealthEventsRoute(healthViewModel, navController)
        }
    }
}
