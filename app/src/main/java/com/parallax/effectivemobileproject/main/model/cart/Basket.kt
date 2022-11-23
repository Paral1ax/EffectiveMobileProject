package com.parallax.effectivemobileproject.main.model.cart

import com.parallax.effectivemobileproject.main.model.main.Item

class Basket(
    val id: Long,
    val basket: List<CartItem>,
    val delivery: String,
    var total: Double
) {

}