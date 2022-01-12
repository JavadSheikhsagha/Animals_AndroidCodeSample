package com.example.local.roomDatabase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductDaoModel::class],version = 1)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao() : ProductDao
}