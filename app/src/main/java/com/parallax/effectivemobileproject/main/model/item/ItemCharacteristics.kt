package com.parallax.effectivemobileproject.main.model.item

import kotlin.collections.List

class ItemCharacteristics(
    val CPU: String,
    val camera: String,
    val color: List<String>,
    val capacity: List<Int>,
    val id: Long,
    val images: List<String>,
    val isFavorites: Boolean,
    val price: Double,
    val rating: Double,
    val sd: String,
    val ssd: String,

    ) {

}
