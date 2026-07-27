package com.cmps.dogtrainingapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.cmps.dogtrainingapp.data.local.AppDatabase
import com.cmps.dogtrainingapp.data.local.dao.PetDao
import com.cmps.dogtrainingapp.data.repository.DailyRecommendationsRepository
import com.cmps.dogtrainingapp.data.repository.HealthEventRepository
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.TrainingRepository
import com.cmps.dogtrainingapp.data.repository.WalkHubRepository
import com.cmps.dogtrainingapp.data.repository.WeightRepository
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import com.cmps.dogtrainingapp.data.source.json.RecsJsonSource
import com.cmps.dogtrainingapp.data.source.json.TrainingJsonSource
import com.cmps.dogtrainingapp.ui.navigation.AppNavGraph
import com.cmps.dogtrainingapp.ui.navigation.CustomBottomNav
import com.cmps.dogtrainingapp.ui.screens.dashboard.DashboardViewModel
import com.cmps.dogtrainingapp.ui.screens.dashboard.DashboardViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventViewModel
import com.cmps.dogtrainingapp.ui.screens.health.HealthEventViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.health.addEditEvent.AddEditViewModel
import com.cmps.dogtrainingapp.ui.screens.health.addEditEvent.AddEditViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileRoute
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileScreen
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModel
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.training.TrainingViewModel
import com.cmps.dogtrainingapp.ui.screens.training.TrainingViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.training.course.CourseViewModel
import com.cmps.dogtrainingapp.ui.screens.training.course.CourseViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.training.lesson.LessonViewModel
import com.cmps.dogtrainingapp.ui.screens.training.lesson.LessonViewModelFactory
import com.cmps.dogtrainingapp.ui.screens.walk.WalkViewModel
import com.cmps.dogtrainingapp.ui.screens.walk.WalkViewModelFactory
import com.cmps.dogtrainingapp.ui.theme.DogTrainingAppTheme
import com.cmps.dogtrainingapp.ui.theme.LightGray1
import com.cmps.dogtrainingapp.utils.applyFullscreen

class MainActivity : ComponentActivity() {

    private val db by lazy { AppDatabase.getInstance(applicationContext) }

    private val petDao by lazy { db.petDao() }
    private val walkDao by lazy { db.walkEventDao() }
    private val weightDao by lazy { db.weightEntryDao() }
    private val healthDao by lazy { db.healthEventDao() }
    private val lessonDao by lazy { db.lessonProgressDao() }

    private val petPrefs by lazy { SelectedPetPreferences(applicationContext) }
    private val trainingJsonSource by lazy { TrainingJsonSource(applicationContext) }
    private val recsJsonSource by lazy { RecsJsonSource(applicationContext) }

    private val profileRepo by lazy { PetRepository(petDao, petPrefs) }
    private val walkRepo by lazy { WalkHubRepository(walkDao, petPrefs) }
    private val weightRepo by lazy { WeightRepository(weightDao, petPrefs) }
    private val healthRepo by lazy { HealthEventRepository(healthDao, petPrefs) }
    private val trainingRepo by lazy { TrainingRepository(trainingJsonSource, lessonDao, petPrefs) }
    private val recsRepository by lazy { DailyRecommendationsRepository(recsJsonSource) }

    private val dashboardViewModel: DashboardViewModel by viewModels {
        DashboardViewModelFactory(recsRepository, profileRepo) }
    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(profileRepo, weightRepo) }
    private val walkViewModel: WalkViewModel by viewModels {
        WalkViewModelFactory(walkRepo) }
    private val healthViewModel: HealthEventViewModel by viewModels {
        HealthEventViewModelFactory(healthRepo) }
    private val trainingViewModel: TrainingViewModel by viewModels {
        TrainingViewModelFactory(trainingRepo) }
    private val courseViewModel: CourseViewModel by viewModels {
        CourseViewModelFactory(trainingRepo) }
    private val lessonViewModel: LessonViewModel by viewModels {
        LessonViewModelFactory(trainingRepo) }
    private val addEditViewModel: AddEditViewModel by viewModels {
        AddEditViewModelFactory(healthRepo) }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        applyFullscreen()


        setContent {
            DogTrainingAppTheme {

                val navController = rememberNavController()

                val currentBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute = currentBackStackEntry?.destination?.route

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LightGray1)
                ) {

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {

                        AppNavGraph(
                            navController = navController,
                            dashboardViewModel = dashboardViewModel,
                            profileViewModel = profileViewModel,
                            walkViewModel = walkViewModel,
                            healthViewModel = healthViewModel,
                            trainingViewModel = trainingViewModel,
                            courseViewModel = courseViewModel,
                            lessonViewModel = lessonViewModel,
                            addEditViewModel = addEditViewModel
                        )
                    }

                    CustomBottomNav(
                        currentRoute = currentRoute,
                        navController = navController
                    )
                }
            }
        }
    }
}
