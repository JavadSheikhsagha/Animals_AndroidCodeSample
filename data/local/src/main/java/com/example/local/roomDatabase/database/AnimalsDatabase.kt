package com.example.local.roomDatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.local.roomDatabase.dao.AnimalDao
import com.example.local.roomDatabase.model.AnimalDaoModel

@Database(entities = [AnimalDaoModel::class],version = 1)
abstract class AnimalsDatabase: RoomDatabase() {
    abstract fun productDao() : AnimalDao
}