package com.example.local.roomDatabase.dao

import com.example.local.roomDatabase.model.AnimalDaoModel
import javax.inject.Inject

class AnimalDao_ImplFile @Inject constructor(private val dao: AnimalDao) {

    suspend fun saveAnimal(animalDaoModel: AnimalDaoModel) {
        dao.saveAnimal(animalDaoModel)
    }

    suspend fun getAllAnimals() : List<AnimalDaoModel> {
        return dao.getAllAnimals()
    }
}