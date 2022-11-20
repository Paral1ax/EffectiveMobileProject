package com.parallax.effectivemobileproject.main.mainpage.adapter.bestsellers

import android.app.Activity
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.itempage.ItemFragment
import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.Item

class BestSellersAdapter(private val activity: Activity): AdapterDelegate<List<Item>>() {

    private var inflater: LayoutInflater = activity.layoutInflater
    private val imageLoader = ImageLoader(activity)

    override fun isForViewType(items: List<Item>, position: Int): Boolean {
        return items[position] is BestSellerItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return BestSellersViewHolder(inflater.inflate(R.layout.fragment_best_sellers_item, parent,false))
    }

    override fun onBindViewHolder(
        items: List<Item>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as BestSellersViewHolder
        val bestSellerItem = items[position] as BestSellerItem
        bindValues(vh, bestSellerItem)
        Log.d("API", "Загрузка фото номер $position во вьюпеджер прошла успешно")
        vh.itemView.setOnClickListener {
            val act = it.context as AppCompatActivity
            val fragment = ItemFragment()
            act.supportFragmentManager.beginTransaction().replace(R.id.activity_fragment, fragment).addToBackStack(null).commit()
        }
    }

    private fun bindValues(holder: BestSellersViewHolder, item: BestSellerItem) {
        val request = ImageRequest.Builder(activity)
            .data(item.picture)
            .target { drawable ->
                holder.itemImage.setImageDrawable(drawable)
            }
            .build()
        val disposable = imageLoader.enqueue(request)
        holder.itemName.text = item.title
        holder.itemPrevCost.text = "$${item.price_without_discount}"
        holder.itemPrevCost.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemCurrentCost.text = "$${item.discount_price}"
    }

    class BestSellersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemImage: ImageView
        var itemPrevCost: TextView
        var itemCurrentCost: TextView
        var like: View

        init {
            itemName = itemView.findViewById(R.id.best_seller_name)
            itemImage = itemView.findViewById(R.id.best_seller_image)
            itemPrevCost = itemView.findViewById(R.id.best_seller_old_cost)
            itemCurrentCost = itemView.findViewById(R.id.best_seller_new_cost)
            like = itemView.findViewById(R.id.best_sellers_like)
        }
    }
}