package com.example.medicare

data class CartItem(
    val name: String,
    val price: Double,
    var quantity: Int = 1, // Default quantity is 1
    val imageRes: Int      // Resource ID for the drawable image
)