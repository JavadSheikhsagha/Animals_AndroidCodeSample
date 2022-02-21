package com.example.network.mappers

import com.example.entity.models.AnimalModel
import com.example.models.dtoModels.responseModels.AnimalResponseModel

object AnimalDtoMapper {

    fun mapFromDtoToEntityList(animalResponseModel: List<AnimalResponseModel>) : List<AnimalModel> {
        return animalResponseModel.map {
            return@map AnimalModel(
                name = it.name,
                latinName = it.latin_name,
                animalType = it.animal_type,
                activeTime = it.active_time,
                lengthMin = it.length_min.toInt(),
                lengthMax = it.length_max.toInt(),
                weightMax = it.weight_max.toInt(),
                weightMin = it.weight_min.toInt(),
                lifespan = it.lifespan.toInt(),
                habitat = it.habitat,
                diet = it.diet,
                geoRange = it.geo_range,
                imageUrl = it.image_link,
            )
        }
    }

    fun mapFromDtoToEntity(it: AnimalResponseModel) : AnimalModel {
        return AnimalModel(
            name = it.name,
            latinName = it.latin_name,
            animalType = it.animal_type,
            activeTime = it.active_time,
            lengthMin = it.length_min.toInt(),
            lengthMax = it.length_max.toInt(),
            weightMax = it.weight_max.toInt(),
            weightMin = it.weight_min.toInt(),
            lifespan = it.lifespan.toInt(),
            habitat = it.habitat,
            diet = it.diet,
            geoRange = it.geo_range,
            imageUrl = it.image_link,
        )
    }

}