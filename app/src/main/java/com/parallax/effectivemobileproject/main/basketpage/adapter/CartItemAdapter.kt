package com.parallax.effectivemobileproject.main.basketpage.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.mainpage.adapter.RecyclerViewClickListener
import com.parallax.effectivemobileproject.main.model.cart.CartItem
import com.parallax.effectivemobileproject.main.model.main.Item

class CartItemAdapter(private val activity: Activity): AdapterDelegate<List<Item>>() {

    val inflater = activity.layoutInflater
    private val imageLoader = ImageLoader(activity)

    override fun isForViewType(items: List<Item>, position: Int): Boolean {
        return items[position] is CartItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CartItemViewHolder(inflater.inflate(R.layout.cart_recyclerview_fragment, parent, false))
    }

    override fun onBindViewHolder(
        items: List<Item>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as CartItemViewHolder
        val item = items[position] as CartItem
        val request = ImageRequest.Builder(activity)
            .data(item.images)
            .target { drawable ->
                vh.image.setImageDrawable(drawable)
            }
            .build()
        val disposable = imageLoader.enqueue(request)
        item.count++
        vh.brandName.text = item.title
        vh.totalPrice.text = "${item.getTotalPrice()}$"
        vh.countItem.text = item.getItemCount().toString()
    }

    class CartItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var brandName: TextView
        var totalPrice: TextView
        var addItemButton: ImageButton
        var subtractItemButton: ImageButton
        var countItem: TextView
        var deleteButton: ImageButton

        init {
            image = itemView.findViewById(R.id.cart_item_image)
            brandName = itemView.findViewById(R.id.cart_item_brand_name)
            totalPrice = itemView.findViewById(R.id.cart_item_total_price)
            addItemButton = itemView.findViewById(R.id.cart_item_add)
            subtractItemButton = itemView.findViewById(R.id.cart_item_substract)
            countItem = itemView.findViewById(R.id.cart_item_count)
            deleteButton = itemView.findViewById(R.id.cart_item_delete)
        }

    }
}