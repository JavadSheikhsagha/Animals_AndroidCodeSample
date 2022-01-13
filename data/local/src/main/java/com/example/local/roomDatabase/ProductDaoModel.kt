package com.example.local.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * I know that if there be a model in a model(which i believe there is..),
 *      i have to add a model as a column which annotated with *@Embedded*
 *
 *      Javad, 13/01/2022
 */

@Entity(tableName = "products")
data class ProductDaoModel(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "boot_title") val boothTitle: String,
    @ColumnInfo(name = "price") val price: Long,
    @ColumnInfo(name = "weight") val weight: Long,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "rate_count") val rateCount: Int,
) {
}