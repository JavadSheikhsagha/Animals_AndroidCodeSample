package com.example.local.roomDatabase.dao

import com.example.local.roomDatabase.model.AnimalDaoModel
import javax.inject.Inject

class AnimalDaoImpl @Inject constructor(private val dao: AnimalDao) {

    suspend fun saveAnimal(animalDaoModel: AnimalDaoModel) = dao.saveAnimal(animalDaoModel)

    fun getAllAnimals() = dao.getAllAnimals()

    fun deleteAllAnimals() = dao.deleteAllAnimals()

}