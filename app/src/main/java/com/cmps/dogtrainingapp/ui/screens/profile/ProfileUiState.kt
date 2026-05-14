package com.cmps.dogtrainingapp.ui.screens.profile

import android.net.Uri
import com.cmps.dogtrainingapp.R
import com.cmps.dogtrainingapp.data.local.entity.Gender
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.local.entity.WeightEntryEntity
import java.io.File

data class ProfileUiState(
    val currentPet: PetEntity? = null,
    val currentPetWeight: WeightEntryEntity? = null,
    val allPets: List<PetEntity> = emptyList(),

    val isLoading: Boolean = true,
    val isEditing: Boolean = false,

    val editedName: String = "",
    val editedBreed: String = "",
    val editedDateOfBirth: String? = "",
    val editedWeight: String = "",
    val editedGender: Gender = Gender.OTHER,
    val editedImageUri: String? = null,

    val errorMessage: String? = null
)