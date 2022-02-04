package com.example.entity.models

data class AnimalModel(
    val name:String,
    val latinName:String,
    val animalType:String,
    val activeTime:String,
    val lengthMin:Int,
    val lengthMax:Int,
    val weightMax:Int,
    val weightMin:Int,
    val lifespan:Int,
    val habitat:String,
    val diet:String,
    val geoRange:String,
    val imageUrl:String
) {
}