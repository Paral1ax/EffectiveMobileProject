package com.parallax.effectivemobileproject.main.itempage.adapter.color

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.parallax.effectivemobileproject.main.itempage.adapter.capacity.CapacityAdapter

class MainColorAdapter(private val activity: Activity, private val colors: MutableList<Any>):
    ListDelegationAdapter<List<Any>>() {

        init {
            delegatesManager.addDelegate(CapacityAdapter(activity))
            delegatesManager.addDelegate(ColorAdapter(activity))
            setItems(colors)
        }
}