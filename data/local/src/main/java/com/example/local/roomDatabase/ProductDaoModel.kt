package com.example.local.roomDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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