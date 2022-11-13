package com.parallax.effectivemobileproject.main.mainpage.bottomtapbar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parallax.effectivemobileproject.R

class TapbarFragment : Fragment() {

    companion object {
        fun newInstance() = TapbarFragment()
    }

    private lateinit var viewModel: TapbarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tapbar, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TapbarViewModel::class.java)
        // TODO: Use the ViewModel
    }

}