package com.example.repository.animal

import com.example.entity.models.AnimalModel
import com.example.repository.utils.State
import kotlinx.coroutines.flow.Flow

interface AnimalRepository {

    fun getAnimals(): Flow<State<List<AnimalModel>?>>
}