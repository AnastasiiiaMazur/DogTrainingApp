package com.cmps.dogtrainingapp.ui.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.Gender
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
        viewModelScope.launch {
            petRepo.initializeDefaultPetIfNeeded()
        }
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

    fun onSaveClicked() {
        val currentPet = uiState.currentPet ?: return
        uiState = uiState.copy( nameOrBreedError = null )

        if (uiState.editedName.isBlank() || uiState.editedBreed.isBlank()) {
            uiState = uiState.copy(
                nameOrBreedError = "Name and Breed fields cannot be empty!"
            )
            return
        }

        val pet = currentPet.copy(
            name = uiState.editedName.trim(),
            breed = uiState.editedBreed.trim(),
            dateOfBirth = uiState.editedDateOfBirth,
            gender = uiState.editedGender
        )

        viewModelScope.launch {
            petRepo.updatePet(pet)

            uiState = uiState.copy( isEditing = false )
        }
    }

    fun onEditClicked() {
        uiState = uiState.copy(
            isEditing = true,
            editedName = uiState.currentPet?.name ?: "",
            editedBreed = uiState.currentPet?.breed ?: "",
            editedWeight = uiState.currentPetWeight?.weightKg?.toString() ?: "",
            editedGender = uiState.currentPet?.gender ?: Gender.OTHER,
            editedDateOfBirth = uiState.currentPet?.dateOfBirth ?: "",
            nameOrBreedError = null
        )
    }

    fun onNameChanged(newName: String) {
        uiState = uiState.copy(
            editedName = newName
        )
    }

    fun onBreedChanged(newBreed: String) {
        uiState = uiState.copy(
            editedBreed = newBreed
        )
    }

    fun onWeightChanged(newWeight: String) {
        uiState = uiState.copy(
            editedWeight = newWeight
        )
    }

    fun onGenderChanged(gender: Gender) {
        uiState = uiState.copy(
            editedGender = gender
        )
    }

    fun onDateOfBirthChanged(dateOfBirth: String) {
        uiState = uiState.copy(
            editedDateOfBirth = dateOfBirth
        )
    }
}