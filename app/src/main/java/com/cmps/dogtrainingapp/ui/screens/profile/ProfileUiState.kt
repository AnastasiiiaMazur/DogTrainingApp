package com.cmps.dogtrainingapp.ui.screens.profile

import com.cmps.dogtrainingapp.data.local.entity.PetEntity

data class ProfileUiState(
    val currentPet: PetEntity? = null,
    val allPets: List<PetEntity> = emptyList(),
    val isLoading: Boolean = true
)