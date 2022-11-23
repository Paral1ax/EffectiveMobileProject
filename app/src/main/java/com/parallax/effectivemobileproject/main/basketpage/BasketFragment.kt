package com.parallax.effectivemobileproject.main.basketpage

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.MainActivity
import com.parallax.effectivemobileproject.main.basketpage.adapter.CartItemAdapter
import com.parallax.effectivemobileproject.main.basketpage.adapter.MainAdapter
import com.parallax.effectivemobileproject.main.itempage.ItemViewModel
import com.parallax.effectivemobileproject.main.model.cart.Basket
import com.parallax.effectivemobileproject.main.model.main.Item
import org.w3c.dom.Text

class BasketFragment : Fragment() {

    private lateinit var viewModel: BasketViewModel
    private lateinit var cartRecycler: RecyclerView
    private lateinit var backButton: ImageButton
    private lateinit var addGeoButton: ImageButton
    private lateinit var checkout: Button
    private lateinit var basketPrice: TextView
    private lateinit var delivery: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        viewModel.getUserBasketItems(this.activity as MainActivity)
        viewModel.userBasket.observe(viewLifecycleOwner) {
            initCartRecyclerview(getRecyclerviewItems())
            totalPriceAndDelivery(it)
        }
    }

    private fun bindViews(view: View) {
        viewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        cartRecycler = view.findViewById(R.id.user_cart_recyclerview)
        backButton = view.findViewById(R.id.basket_back_button)
        addGeoButton = view.findViewById(R.id.add_new_geo_button)
        checkout = view.findViewById(R.id.checkout_button)
        basketPrice = view.findViewById(R.id.total_cost_textview)
        delivery = view.findViewById(R.id.delivey_cost_textview)
    }

    private fun initCartRecyclerview(list: MutableList<Item>) {
        val adapter = MainAdapter(this.requireActivity(), list)
        cartRecycler.layoutManager = LinearLayoutManager(this.requireContext())
        cartRecycler.adapter = adapter
    }

    private fun totalPriceAndDelivery(basket: Basket) {
        basketPrice.text = "$${basket.total} us"
        delivery.text = basket.delivery
    }

    private fun getRecyclerviewItems(): MutableList<Item> {
        val list = mutableListOf<Item>()
        viewModel.userBasket.value?.let { list.addAll(it.basket) }
        return list
    }
}