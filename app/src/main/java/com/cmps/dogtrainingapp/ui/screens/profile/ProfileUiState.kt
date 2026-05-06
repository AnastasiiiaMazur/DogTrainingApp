package com.cmps.dogtrainingapp.ui.screens.profile

import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity

data class ProfileUiState(
    val currentPet: PetEntity? = null,
    val currentPetWeight: WeightEntryEntity? = null,
    val allPets: List<PetEntity> = emptyList(),
    val isLoading: Boolean = true
)