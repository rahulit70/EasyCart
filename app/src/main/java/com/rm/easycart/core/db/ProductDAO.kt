package com.rm.easycart.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.AppConstants

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product): Long

    @Query("SELECT * FROM ${AppConstants.TABLE_PRODUCT}")
    fun getProducts(): List<Product>

    @Insert
    fun insertAll(vararg product: Product)

    @Delete
    fun delete(product: Product)
}