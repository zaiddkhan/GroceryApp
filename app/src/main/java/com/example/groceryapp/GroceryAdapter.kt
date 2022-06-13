package com.example.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryapp.room.GroceryItem
import org.w3c.dom.Text

class GroceryAdapter(var list:List<GroceryItem>,val groceryClickInterface:GroceryItemClick)
    :RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>(){

    inner class GroceryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val nameTv = itemView.findViewById<TextView>(R.id.txtItemName)
        val quatityTv = itemView.findViewById<TextView>(R.id.txtItemQuantity)
        val rateTv = itemView.findViewById<TextView>(R.id.txtItemPrice)
        val amount = itemView.findViewById<TextView>(R.id.txtItemTotalCost)
        val delete = itemView.findViewById<ImageView>(R.id.ibDelete)
    }


  interface GroceryItemClick{
      fun onItemClick(groceryItem: GroceryItem)
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item,parent,false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        val item = list.get(position)
        holder.nameTv.text = item.itemName
        holder.quatityTv.text = item.itemQuantity.toString()
        holder.rateTv.text = "Rs ${item.itemPrice}"
        val amountTotal = item.itemPrice * item.itemQuantity
        holder.amount.text = "Rs. "+amountTotal.toString()
        holder.delete.setOnClickListener {
            groceryClickInterface.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}