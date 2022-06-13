package com.example.groceryapp.room

class GroceryRepository(val db:Grocerydatabase) {

    suspend fun insert(item:GroceryItem) = db.getGroceryDao().insert(item)

    suspend fun delete(item: GroceryItem) = db.getGroceryDao().delete(item)

    fun getAll() = db.getGroceryDao().getAllItems()
}