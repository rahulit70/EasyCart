package com.rm.easycart.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rm.easycart.core.model.Product

@Database(entities = [Product::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): ProductDAO
}