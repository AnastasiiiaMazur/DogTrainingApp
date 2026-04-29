package com.cmps.dogtrainingapp.data.source

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SelectedPetPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        SelectedPetPreferences.Companion.PREF_NAME,
        Context.MODE_PRIVATE)

    fun getSelectedPetId(): Long {
        return sharedPreferences.getLong(PET_ID, 0)
    }

    fun saveSelectedPetId(petId: Long) {
        sharedPreferences.edit { putLong(PET_ID, petId) }
    }

    companion object {
        private const val PREF_NAME = "dog_app_prefs"
        private const val PET_ID = "petId"
    }
}
