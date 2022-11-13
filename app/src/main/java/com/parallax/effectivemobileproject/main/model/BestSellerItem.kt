package com.parallax.effectivemobileproject.main.model

class BestSellerItem(
    id: Long,
    title: String,
    subtitle: String,
    picture: String,
    var is_favorites: Boolean,
    var price_without_discount: Double,
    var discount_price: Double
): Item(id, title, subtitle, picture) {

}