package com.cmps.dogtrainingapp.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ProfileViewModel(private val petRepo: PetRepository) : ViewModel() {

    var uiState by mutableStateOf(ProfileUiState())
        private set

    init {
        loadPets()
        loadCurrentPet()
    }

    private fun loadPets() {
        viewModelScope.launch {
            petRepo.getAllPets().collect { pets ->
                uiState = uiState.copy(
                    allPets = pets,
                    isLoading = false
                )
            }
        }
    }

    private fun loadCurrentPet() {
        viewModelScope.launch {
            petRepo.getCurrentPet().collect { pet ->
                uiState = uiState.copy(
                    currentPet = pet,
                    isLoading = false
                )
            }
        }
    }
}