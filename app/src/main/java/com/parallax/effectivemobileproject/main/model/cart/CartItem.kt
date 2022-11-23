package com.parallax.effectivemobileproject.main.model.cart

import com.parallax.effectivemobileproject.main.model.main.Item

class CartItem(
    id: Long,
    title: String,
    private var price: Int,
    var images: String,
    picture: String,
): Item(id, title, price.toString(), picture) {

    var count: Int = 1

    fun getTotalPrice(): Double {
        return price * count.toDouble()
    }

    fun getItemCount(): Int {
        return if (count >= 0)
            count
        else 0
    }

}