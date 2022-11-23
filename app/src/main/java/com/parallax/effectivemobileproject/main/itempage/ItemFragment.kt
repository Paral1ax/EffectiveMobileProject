package com.parallax.effectivemobileproject.main.itempage

import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.itempage.adapter.MainColorAdapter
import com.parallax.effectivemobileproject.main.itempage.adapter.photo.ImageAdapter
import com.parallax.effectivemobileproject.main.itempage.details.DetailsAdapter
import com.parallax.effectivemobileproject.main.itempage.details.details.DetailsFragment
import com.parallax.effectivemobileproject.main.itempage.details.features.FeatureFragment
import com.parallax.effectivemobileproject.main.itempage.details.shop.ShopFragment
import kotlin.math.abs

class ItemFragment : Fragment() {

    companion object {
        private lateinit var viewModel: ItemViewModel
        fun thisViewModel() = this.viewModel
    }
    private lateinit var imagesViewPager: ViewPager2
    private lateinit var colorCapacityRecycler: RecyclerView
    private lateinit var tabBar: TabLayout
    private lateinit var tabViewPager: ViewPager2
    private lateinit var addToCard: Button
    private lateinit var itemFavorite: ConstraintLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        viewModel.responseData.observe(viewLifecycleOwner) {
            initCustomRecycler()
            initImageViewPager()
            initDetailsViewPager()

            itemFavorite.setOnClickListener {
                like()
            }
            addToCard.text = "${addToCard.text}${getString(R.string.tab)}$${viewModel.responseData.value!!.price}"
        }

    }

    private fun bindViews(view: View) {
        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        imagesViewPager = view.findViewById(R.id.item_viewpager)
        colorCapacityRecycler = view.findViewById(R.id.color_capacity_recyclerview)
        viewModel.getItemCharacteristics(this.activity as MainActivity)
        tabBar = view.findViewById(R.id.item_fragment_tablayout)
        tabViewPager = view.findViewById(R.id.item_details_viewpager)
        addToCard = view.findViewById(R.id.add_to_cart_button)
        itemFavorite = view.findViewById(R.id.item_fragment_liked)
    }

    private fun viewpagerTransformer() {
        val transformation = CompositePageTransformer()
        transformation.addTransformer(MarginPageTransformer(40))
        transformation.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        imagesViewPager.setPageTransformer(transformation)
    }

    private fun initCustomRecycler() {
        val concatList = concatColorAndCapacity()
        val adapter = MainColorAdapter(this.requireActivity(), concatList)
        colorCapacityRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        colorCapacityRecycler.adapter = adapter
    }

    private fun initImageViewPager() {
        val images = viewModel.responseData.value?.images?.toMutableList()
        val adapter = ImageAdapter(this.requireActivity())
        imagesViewPager.adapter = adapter
        imagesViewPager.offscreenPageLimit = 3
        imagesViewPager.clipToPadding = false
        imagesViewPager.clipChildren = false
        imagesViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        viewpagerTransformer()
        adapter.setData(images!!)
    }

    private fun concatColorAndCapacity(): MutableList<Any> {
        val concatList = mutableListOf<Any>()
        viewModel.responseData.value?.let { concatList.addAll(it.color) }
        viewModel.responseData.value?.let { concatList.addAll(it.capacity) }
        return concatList
    }

    private fun initDetailsViewPager() {
        val adapter = DetailsAdapter(this.requireActivity())
        tabViewPager.adapter = adapter
        TabLayoutMediator(tabBar, tabViewPager) {tab, position ->
            when(position) {
                0 -> {
                    tab.text = "Shop"
                }
                1 -> {
                    tab.text = "Details"
                }
                else -> {
                    tab.text = "Features"
                }
            }
        }.attach()
    }

    private fun like() {
        val heartView = itemFavorite.children.find {
            it == it.findViewById(R.id.is_item_favorite)
        }
        heartView?.setBackgroundResource(R.drawable.ic_heart_fill_orange)
    }
}