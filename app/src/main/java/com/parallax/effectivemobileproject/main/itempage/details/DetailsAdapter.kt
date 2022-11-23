package com.parallax.effectivemobileproject.main.itempage.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.parallax.effectivemobileproject.main.itempage.ItemFragment
import com.parallax.effectivemobileproject.main.itempage.details.details.DetailsFragment
import com.parallax.effectivemobileproject.main.itempage.details.features.FeatureFragment
import com.parallax.effectivemobileproject.main.itempage.details.shop.ShopFragment


class DetailsAdapter(fragment: FragmentActivity): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShopFragment()
            1 -> DetailsFragment()
            else -> FeatureFragment()
        }
    }

}