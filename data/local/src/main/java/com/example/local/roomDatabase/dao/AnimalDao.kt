package com.example.local.roomDatabase.dao

import androidx.room.*
import com.example.local.roomDatabase.model.AnimalDaoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAnimal(animal: AnimalDaoModel)

    @Query("SELECT * FROM animals")
    fun getAllAnimals() : Flow<List<AnimalDaoModel>>

    @Query("DELETE FROM animals")
    fun deleteAllAnimals()
}