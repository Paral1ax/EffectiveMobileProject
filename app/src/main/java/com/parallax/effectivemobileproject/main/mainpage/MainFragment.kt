package com.parallax.effectivemobileproject.main.mainpage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.get
import androidx.viewpager2.widget.ViewPager2
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.mainpage.hotsales.MainViewModelFactory
import com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager.HotSalesAdapter
import com.parallax.effectivemobileproject.main.repository.Repository

class MainFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.getHotSales()
        viewModel.mainModel.observe(viewLifecycleOwner, Observer { response ->
            Log.d("Response", response.body().toString())
        })
        viewPager = view.findViewById(R.id.hotsales_viewpager)
        viewPager.adapter = HotSalesAdapter()

    }
}