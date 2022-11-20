package com.parallax.effectivemobileproject.main.itempage

import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.itempage.adapter.color.MainColorAdapter
import com.parallax.effectivemobileproject.main.model.item.ItemCharacteristics

class ItemFragment : Fragment() {

    companion object {
        fun newInstance() = ItemFragment()
    }
    private lateinit var viewpager: ViewPager2
    private lateinit var viewModel: ItemViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]

        viewpager = view.findViewById(R.id.item_viewpager)
        recyclerView = view.findViewById(R.id.color_capacity_recyclerview)
        viewModel.getItemCharacteristics(this.activity as MainActivity)

        viewModel.capacityColor.observe(viewLifecycleOwner) {
            val adapter = MainColorAdapter(this.requireActivity(), viewModel.capacityColor.value!!)
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
    }
}