package com.example.groceryapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [GroceryItem::class], version = 1)
abstract class Grocerydatabase :RoomDatabase()
{
    abstract fun getGroceryDao():GroceryDao

    companion object{
        @Volatile
        private var instance : Grocerydatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                Grocerydatabase::class.java,
                "Grocery.db")
                .build()
    }

}