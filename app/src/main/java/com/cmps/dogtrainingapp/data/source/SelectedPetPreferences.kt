package com.cmps.dogtrainingapp.data.source

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SelectedPetPreferences(
    context: Context
) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(
            PREF_NAME,
            Context.MODE_PRIVATE
        )

    private val _selectedPetId = MutableStateFlow(
        sharedPreferences.getLong(PET_ID, 0)
    )

    val selectedPetIdFlow: StateFlow<Long> =
        _selectedPetId

    fun getSelectedPetId(): Long {
        return _selectedPetId.value
    }

    fun saveSelectedPetId(petId: Long) {

        sharedPreferences.edit {
            putLong(PET_ID, petId)
        }

        _selectedPetId.value = petId
    }

    companion object {
        private const val PREF_NAME = "dog_app_prefs"
        private const val PET_ID = "petId"
    }
}
