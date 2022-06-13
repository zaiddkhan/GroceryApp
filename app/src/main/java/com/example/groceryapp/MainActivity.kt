package com.example.groceryapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.room.GroceryItem
import com.example.groceryapp.room.GroceryRepository
import com.example.groceryapp.room.Grocerydatabase
import com.example.groceryapp.room.ViewModelFactory

class MainActivity : AppCompatActivity() ,GroceryAdapter.GroceryItemClick{

    lateinit var recView:RecyclerView
    lateinit var btn :Button
    lateinit var list:List<GroceryItem>
    lateinit var adapter: GroceryAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.rvList)
        btn = findViewById(R.id.btnAdd)
        list = ArrayList<GroceryItem>()
        adapter = GroceryAdapter(list,this)
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter
        val repo = GroceryRepository(Grocerydatabase(this))
        val facotry = ViewModelFactory(repo)
        viewModel = ViewModelProvider(this,facotry).get(MainViewModel::class.java)

        viewModel.getAll().observe(this, Observer {
            adapter.list = it
            adapter.notifyDataSetChanged()
        })

        btn.setOnClickListener {
            openDialog()
        }

    }

    fun openDialog(){
          val dialog=Dialog(this)
        dialog.setContentView(R.layout.dialog_add_items)
        val cancel = dialog.findViewById<TextView>(R.id.tvCancel)
        val save = dialog.findViewById<TextView>(R.id.tvSave)
        val itemEdt = dialog.findViewById<EditText>(R.id.etItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.etItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.etItemQuantity)
        cancel.setOnClickListener {
            dialog.dismiss()
        }

        save.setOnClickListener {
            val name = itemEdt.text.toString()
            val itemPrice = itemPriceEdt.text.toString()
            val itemQuantity = itemQuantityEdt.text.toString()

            val qty = itemQuantity.toInt()
            val price = itemPrice.toInt()

            val item = GroceryItem(name,qty,price)
            viewModel.insert(item)
            adapter.notifyDataSetChanged()
            dialog.dismiss()

        }
        dialog.show()
    }

    override fun onItemClick(groceryItem: GroceryItem) {
        viewModel.delete(groceryItem)
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "DELETED", Toast.LENGTH_SHORT).show()
    }

}