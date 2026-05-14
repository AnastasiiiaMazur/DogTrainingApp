package com.cmps.dogtrainingapp.data.repository

import com.cmps.dogtrainingapp.data.local.dao.PetDao
import com.cmps.dogtrainingapp.data.local.entity.Gender
import com.cmps.dogtrainingapp.data.local.entity.PetEntity
import com.cmps.dogtrainingapp.data.source.SelectedPetPreferences
import kotlinx.coroutines.flow.Flow

class PetRepository(
    private val petDao: PetDao,
    private val petPrefs: SelectedPetPreferences
) {

    fun getAllPets(): Flow<List<PetEntity>> {
        return petDao.getAll()
    }

    fun getSelectedPetId(): Long {
        return petPrefs.getSelectedPetId()
    }

    fun selectPet(id: Long){
        petPrefs.saveSelectedPetId(id)
    }

    suspend fun addPet(pet: PetEntity){
        petDao.insert(pet)
    }

    suspend fun updatePet(pet: PetEntity) {
        petDao.update(pet)
    }

    suspend fun deletePet(pet: PetEntity) {
        petDao.delete(pet)
    }

    fun getCurrentPet(): Flow<PetEntity?> {
        return petDao.getByIdFlow(getSelectedPetId())
    }

    suspend fun countPets(): Int {
        return petDao.getPetCountOnce()
    }

    suspend fun initializeDefaultPetIfNeeded() {
        if (countPets() == 0) {
            val defaultPet = PetEntity(
                name = "My Pet",
                breed = "",
                dateOfBirth = "",
                gender = Gender.OTHER,
                imageUri = "dog_default"
            )

            val newPetId = petDao.insert(defaultPet)
            petPrefs.saveSelectedPetId(newPetId)
        }
    }

    suspend fun addDefaultPet(): Long {
        val newPet = PetEntity(
            name = "My Pet",
            breed = "",
            dateOfBirth = "",
            gender = Gender.OTHER,
            imageUri = null
        )

        val newPetId = petDao.insert(newPet)
        petPrefs.saveSelectedPetId(newPetId)

        return newPetId
    }

}
