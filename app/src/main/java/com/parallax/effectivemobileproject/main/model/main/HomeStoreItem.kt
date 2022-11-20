package com.parallax.effectivemobileproject.main.model.main

class HomeStoreItem(
    id: Long,
    title: String,
    subtitle: String,
    picture: String,
    var is_new: Boolean,
    var is_buy: Boolean,
): Item(id, title, subtitle, picture) {

}