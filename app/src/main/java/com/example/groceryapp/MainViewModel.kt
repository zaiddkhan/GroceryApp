package com.example.groceryapp

import androidx.lifecycle.ViewModel
import com.example.groceryapp.room.GroceryItem
import com.example.groceryapp.room.GroceryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(private val repo:GroceryRepository):ViewModel() {

    fun insert(item: GroceryItem) = GlobalScope.launch {
        repo.insert(item)
    }

    fun delete(item:GroceryItem) = GlobalScope.launch {
        repo.delete(item)
    }

    fun getAll() = repo.getAll()
}