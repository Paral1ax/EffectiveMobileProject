package com.parallax.effectivemobileproject.main.model

import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.HomeStoreItem

data class MainApiModel(
    val home_store: List<HomeStoreItem>,
    val best_seller: List<BestSellerItem>
) {

}