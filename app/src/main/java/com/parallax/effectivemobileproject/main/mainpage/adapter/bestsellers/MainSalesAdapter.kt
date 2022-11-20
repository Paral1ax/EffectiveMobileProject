package com.parallax.effectivemobileproject.main.mainpage.adapter.bestsellers

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.parallax.effectivemobileproject.main.mainpage.adapter.bestsellers.BestSellersAdapter
import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.Item

class MainSalesAdapter(private val activity: Activity, private val sales: MutableList<BestSellerItem>): ListDelegationAdapter<List<Item>>() {

    init {
        delegatesManager.addDelegate(BestSellersAdapter(activity))

        setItems(sales)
    }
}