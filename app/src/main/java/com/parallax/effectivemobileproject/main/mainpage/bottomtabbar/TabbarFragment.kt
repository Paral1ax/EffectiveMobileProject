package com.parallax.effectivemobileproject.main.mainpage.bottomtabbar

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.basketpage.BasketFragment

class TabbarFragment : Fragment() {

    companion object {
        fun newInstance() = TabbarFragment()
    }

    private lateinit var viewModel: TabbarViewModel
    private lateinit var basketButton: ImageButton
    private lateinit var favoritesButton: ImageButton
    private lateinit var userProfileButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tapbar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[TabbarViewModel::class.java]
        bindViews(view)
        basketButton.setOnClickListener {
            val fragment = BasketFragment()
            activity?.supportFragmentManager!!.beginTransaction().replace(R.id.activity_fragment, fragment).addToBackStack(null).commit()
        }

    }

    private fun bindViews(view: View) {
        basketButton = view.findViewById(R.id.basketFragmentButton)
        favoritesButton = view.findViewById(R.id.favoriteFragmentButton)
        userProfileButton = view.findViewById(R.id.userProfileFragmentButton)
    }
}