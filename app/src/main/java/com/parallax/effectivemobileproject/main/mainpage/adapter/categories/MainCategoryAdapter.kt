package com.parallax.effectivemobileproject.main.mainpage.adapter.categories

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.CategoryItem
import com.parallax.effectivemobileproject.main.model.main.Item

class MainCategoryAdapter(private val activity: Activity, private val categories: MutableList<CategoryItem>): ListDelegationAdapter<List<CategoryItem>>() {

    init {
        delegatesManager.addDelegate(CategoryAdapter(activity))

        setItems(categories)
    }
}