package com.example.repository.usecases

import com.example.entity.models.AnimalModel
import com.example.repository.animal.AnimalRepository
import com.example.repository.utils.State
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAnimalUsecase @Inject constructor(private val animalRepository: AnimalRepository) {

    operator fun invoke(): Flow<State<List<AnimalModel>?>> {
        return animalRepository.getAnimals()
    }
}