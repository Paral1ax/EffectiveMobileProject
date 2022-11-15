package com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.parallax.effectivemobileproject.R

class HotSalesFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = HotSalesFragment()
    }

    private lateinit var viewModel: HotSalesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hot_sales, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager = view.findViewById(R.id.hotsales_viewpager)
        viewModel = ViewModelProvider(this)[HotSalesViewModel::class.java]
        viewModel.getHotSales()
        viewModel.responseData.observe(viewLifecycleOwner, Observer {
            val adapter = HotSalesAdapter(this.requireContext())
            viewPager.adapter = adapter
            (context as AppCompatActivity).runOnUiThread {
                adapter.setData(this.viewModel.responseData.value!!)
            }
        })
    }
}