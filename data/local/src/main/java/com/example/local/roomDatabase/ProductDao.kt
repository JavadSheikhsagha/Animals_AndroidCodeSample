package com.example.local.roomDatabase

import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProduct(product: ProductDaoModel)

    @Delete
    suspend fun deleteProduct(product: ProductDaoModel)

    @Query("SELECT * FROM products")
    suspend fun getAllProducts() : List<ProductDaoModel>
}