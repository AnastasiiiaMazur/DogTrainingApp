package com.cmps.dogtrainingapp.ui.screens.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cmps.dogtrainingapp.data.local.entity.Gender
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import com.cmps.dogtrainingapp.data.repository.PetRepository
import com.cmps.dogtrainingapp.data.repository.WeightRepository
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.File
import java.time.LocalDate

class ProfileViewModel(
    private val petRepo: PetRepository,
    private val weightRepo: WeightRepository
) : ViewModel() {

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

        viewModelScope.launch {
            weightRepo.getLatestWeight().collect { weight ->
                uiState = uiState.copy(
                    currentPetWeight = weight
                )
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSaveClicked() {
        val currentPet = uiState.currentPet ?: return

        uiState = uiState.copy(
            errorMessage = null
        )

        if (uiState.editedName.isBlank() || uiState.editedBreed.isBlank()) {
            uiState = uiState.copy(
                errorMessage = "Name and Breed fields cannot be empty!"
            )
            return
        }

        val pet = currentPet.copy(
            name = uiState.editedName.trim(),
            breed = uiState.editedBreed.trim(),
            dateOfBirth = uiState.editedDateOfBirth,
            gender = uiState.editedGender,
            imageUri = uiState.editedImageUri
        )

        val currentWeight = uiState.currentPetWeight?.weightKg

        val editedWeightFloat = if (uiState.editedWeight.isBlank()) {
            null
        } else {
            uiState.editedWeight.toFloatOrNull()
        }

        if (uiState.editedWeight.isNotBlank() && editedWeightFloat == null) {
            uiState = uiState.copy(
                errorMessage = "Weight must be a valid number!"
            )
            return
        }

        if (editedWeightFloat != null && editedWeightFloat <= 0f) {
            uiState = uiState.copy(
                errorMessage = "Weight must be greater than 0!"
            )
            return
        }

        viewModelScope.launch {
            petRepo.updatePet(pet)

            if (
                editedWeightFloat != null &&
                (currentWeight == null || currentWeight != editedWeightFloat)
            ) {
                val newWeight = WeightEntryEntity(
                    petId = currentPet.id,
                    date = LocalDate.now(),
                    weightKg = editedWeightFloat
                )

                weightRepo.addNewWeightEntry(newWeight)
            }

            uiState = uiState.copy(
                isEditing = false,
                editedImageUri = pet.imageUri
            )
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
            editedImageUri = uiState.currentPet?.imageUri ?: "",
            errorMessage = null
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

    fun onImageChanged(path: String) {
        uiState = uiState.copy(
            editedImageUri = path
        )
    }

    fun onPetChanged(pet: PetEntity) {
        petRepo.selectPet(pet.id)

        uiState = uiState.copy(
            currentPet = pet,
            currentPetWeight = null,
            editedWeight = ""
        )

        loadCurrentPet()
    }

    fun onAddNewPetClicked() {
        viewModelScope.launch {
            petRepo.addDefaultPet()
        }
    }
}