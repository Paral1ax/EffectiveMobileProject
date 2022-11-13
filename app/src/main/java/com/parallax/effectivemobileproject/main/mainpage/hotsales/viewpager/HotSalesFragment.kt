package com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parallax.effectivemobileproject.R

class HotSalesFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotSalesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}