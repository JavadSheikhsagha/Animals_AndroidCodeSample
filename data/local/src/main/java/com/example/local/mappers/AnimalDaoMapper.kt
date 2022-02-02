package com.example.local.mappers

import com.example.entity.models.AnimalModel
import com.example.local.roomDatabase.model.AnimalDaoModel

object AnimalDaoMapper {

    fun mapFromDaoToEntity(animalDaoModel: AnimalDaoModel) : AnimalModel {
        return AnimalModel(
            name = animalDaoModel.name,
            latinName = animalDaoModel.latinName,
            animalType = animalDaoModel.animalType,
            activeTime = animalDaoModel.activeTime,
            lengthMin = animalDaoModel.lengthMin,
            lengthMax = animalDaoModel.lengthMax,
            weightMax = animalDaoModel.weightMax,
            weightMin = animalDaoModel.weightMin,
            lifespan = animalDaoModel.lifespan,
            habitat = animalDaoModel.habitat,
            diet = animalDaoModel.diet,
            geoRange = animalDaoModel.geoRange,
            imageUrl = animalDaoModel.imageUrl
        )
    }

    fun mapFromEntityToDao(animalModel: AnimalModel): AnimalDaoModel {
        return AnimalDaoModel(
            id = 0,
            name = animalModel.name,
            latinName = animalModel.latinName,
            animalType = animalModel.animalType,
            activeTime = animalModel.activeTime,
            lengthMin = animalModel.lengthMin,
            lengthMax = animalModel.lengthMax,
            weightMax = animalModel.weightMax,
            weightMin = animalModel.weightMin,
            lifespan = animalModel.lifespan,
            habitat = animalModel.habitat,
            diet = animalModel.diet,
            geoRange = animalModel.geoRange,
            imageUrl = animalModel.imageUrl
        )
    }

    fun mapFromEntityToDaoList(animalModel: List<AnimalModel>?) : List<AnimalDaoModel>? {
        return animalModel?.map {
            return@map mapFromEntityToDao(it)
        }
    }

    fun mapFromDaoToEntityList(animalDaoModel: List<AnimalDaoModel>?) : List<AnimalModel>? {
        return animalDaoModel?.map {
            return@map mapFromDaoToEntity(it)
        }
    }
}