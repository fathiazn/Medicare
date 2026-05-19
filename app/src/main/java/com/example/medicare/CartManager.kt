package com.example.medicare

object CartManager {
    val cartItems = mutableListOf<CartItem>()
    var lastOrder = listOf<CartItem>() // snapshot of last order

    fun addItem(item: CartItem) {
        val existing = cartItems.find { it.name == item.name }
        if (existing != null) {
            existing.quantity += item.quantity
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        cartItems.remove(item)
    }

    fun clearCart() {
        lastOrder = cartItems.toList() // ✅ save snapshot before clearing
        cartItems.clear()
    }

    fun getTotal(): Double = cartItems.sumOf { it.price * it.quantity }
}