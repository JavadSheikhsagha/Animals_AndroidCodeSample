package com.example.local.roomDatabase.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * I know that if there be a model in a model(which i believe there is..),
 *      i have to add a model as a column which annotated with *@Embedded*
 *
 *      Javad, 13/01/2022
 */

@Entity(tableName = "animals")
data class AnimalDaoModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latinName") val latinName: String,
    @ColumnInfo(name = "animalType") val animalType: String,
    @ColumnInfo(name = "activeTime") val activeTime: String,
    @ColumnInfo(name = "lengthMin") val lengthMin: Int,
    @ColumnInfo(name = "lengthMax") val lengthMax: Int,
    @ColumnInfo(name = "weightMax") val weightMax: Int,
    @ColumnInfo(name = "weightMin") val weightMin: Int,
    @ColumnInfo(name = "lifespan") val lifespan: Int,
    @ColumnInfo(name = "habitat") val habitat: String,
    @ColumnInfo(name = "diet") val diet: List<String>,
    @ColumnInfo(name = "geoRange") val geoRange: String,
    @ColumnInfo(name = "imageUrl") val imageUrl: String
) {
}