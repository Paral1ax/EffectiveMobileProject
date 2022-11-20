package com.parallax.effectivemobileproject.main.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.mainpage.adapter.RecyclerViewClickListener
import com.parallax.effectivemobileproject.main.mainpage.adapter.bestsellers.MainSalesAdapter
import com.parallax.effectivemobileproject.main.mainpage.adapter.categories.CategoryAdapter
import com.parallax.effectivemobileproject.main.mainpage.adapter.categories.MainCategoryAdapter
import com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager.HotSalesAdapter
import com.parallax.effectivemobileproject.main.model.main.CategoryItem

class MainFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bestSellersRecycler: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var categoriesData: MutableLiveData<MutableList<CategoryItem>>
    private lateinit var categoryRecycler: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewPager = view.findViewById(R.id.hotsales_viewpager)
        bestSellersRecycler = view.findViewById(R.id.best_sellers_recyclerview)
        categoryRecycler = view.findViewById(R.id.category_recycler)
        categoriesData = MutableLiveData()

        viewModel.getHotSales(this.activity as MainActivity)
        categoriesData.value = viewModel.setCategories()

        viewModel.hotSalesData.observe(viewLifecycleOwner) {
            val adapter = HotSalesAdapter(this.requireContext())
            viewPager.adapter = adapter
            adapter.setData(this.viewModel.hotSalesData.value!!)
        }
        viewModel.bestSellersData.observe(viewLifecycleOwner) {
            val adapter = MainSalesAdapter(this.requireActivity(), this.viewModel.bestSellersData.value!!)
            bestSellersRecycler.layoutManager = GridLayoutManager(this.requireContext(), 2)
            bestSellersRecycler.adapter = adapter
        }

        categoriesData.observe(viewLifecycleOwner) {
            val adapter = MainCategoryAdapter(this.requireActivity(), categoriesData.value!!)
            categoryRecycler.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.HORIZONTAL,false)
            categoryRecycler.adapter = adapter
        }


    }
}