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
import com.cmps.dogtrainingapp.data.local.AppDatabase
import com.cmps.dogtrainingapp.data.local.dao.PetDao
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.WeightRepository
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import com.cmps.dogtrainingapp.ui.navigation.BottomNavItem
import com.cmps.dogtrainingapp.ui.navigation.CustomBottomNav
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileRoute
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileScreen
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModel
import com.cmps.dogtrainingapp.ui.screens.profile.ProfileViewModelFactory
import com.cmps.dogtrainingapp.ui.theme.DogTrainingAppTheme
import com.cmps.dogtrainingapp.ui.theme.LightGray1
import com.cmps.dogtrainingapp.utils.applyFullscreen

class MainActivity : ComponentActivity() {

    private val db by lazy { AppDatabase.getInstance(applicationContext) }
    private val petDao by lazy { db.petDao() }
    private val weightDao by lazy { db.weightEntryDao() }
    private val petPrefs by lazy { SelectedPetPreferences(applicationContext) }
    private val profileRepo by lazy { PetRepository(petDao, petPrefs) }
    private val weightRepo by lazy { WeightRepository(weightDao, petPrefs) }
    private val profileViewModel: ProfileViewModel by viewModels {
        ProfileViewModelFactory(profileRepo, weightRepo)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        applyFullscreen()

        setContent {
            DogTrainingAppTheme {

                var selectedTab by remember { mutableStateOf(BottomNavItem.PROFILE) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = LightGray1)
                ) {

                    Box(
                        modifier = Modifier.weight(1f)
                    ) {

                        when (selectedTab) {

                            BottomNavItem.DASHBOARD -> {
                                Box(modifier = Modifier.fillMaxSize())
                            }

                            BottomNavItem.TRAINING_HUB -> {
                                Box(modifier = Modifier.fillMaxSize())
                            }

                            BottomNavItem.PROFILE -> {
                                ProfileRoute(profileViewModel)
                            }

                            BottomNavItem.HEALTH_HUB -> {
                                Box(modifier = Modifier.fillMaxSize())
                            }

                            BottomNavItem.WALK_TRACKER -> {
                                Box(modifier = Modifier.fillMaxSize())
                            }
                        }
                    }

                    CustomBottomNav(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                }
            }
        }
    }
}
