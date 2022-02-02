package com.example.local.roomDatabase.dao

import androidx.room.*
import com.example.local.roomDatabase.model.AnimalDaoModel

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAnimal(animal: AnimalDaoModel)

    @Query("SELECT * FROM animals")
    suspend fun getAllAnimals() : List<AnimalDaoModel>
}