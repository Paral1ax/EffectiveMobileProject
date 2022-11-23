package com.parallax.effectivemobileproject.main.basketpage.adapter

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.parallax.effectivemobileproject.main.model.main.Item

class MainAdapter(private val activity: Activity, private val cartItems: MutableList<Item>): ListDelegationAdapter<List<Item>>() {

        init {
            delegatesManager.addDelegate(CartItemAdapter(activity))

            setItems(cartItems)
        }
}