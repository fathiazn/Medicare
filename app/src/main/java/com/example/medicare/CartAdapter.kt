package com.example.medicare

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onUpdate: () -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgItem: ImageView = view.findViewById(R.id.imgCartItem)
        val txtName: TextView = view.findViewById(R.id.txtCartItemName)
        val txtPrice: TextView = view.findViewById(R.id.txtCartItemPrice)
        val txtQty: TextView = view.findViewById(R.id.txtCartQuantity)
        val btnPlus: Button = view.findViewById(R.id.btnCartPlus)
        val btnMinus: Button = view.findViewById(R.id.btnCartMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imgItem.setImageResource(item.imageRes)
        holder.txtName.text = item.name
        holder.txtPrice.text = "$ ${String.format("%.2f", item.price)}"
        holder.txtQty.text = item.quantity.toString()

        holder.btnPlus.setOnClickListener {
            item.quantity++
            holder.txtQty.text = item.quantity.toString()
            onUpdate()
        }

        holder.btnMinus.setOnClickListener {
            if (item.quantity > 1) {
                item.quantity--
                holder.txtQty.text = item.quantity.toString()
                onUpdate()
            } else {
                items.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, items.size)
                onUpdate()
            }
        }
    }

    override fun getItemCount() = items.size
}