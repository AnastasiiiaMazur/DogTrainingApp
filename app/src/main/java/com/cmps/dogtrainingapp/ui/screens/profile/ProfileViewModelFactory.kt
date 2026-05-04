package com.cmps.dogtrainingapp.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cmps.dogtrainingapp.data.repository.PetRepository


@Suppress("UNCHECKED_CAST")
class ProfileViewModelFactory(private val petRepo: PetRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(petRepo) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}