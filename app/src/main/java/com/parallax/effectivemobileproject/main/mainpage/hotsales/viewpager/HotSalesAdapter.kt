package com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.parallax.effectivemobileproject.R
import java.lang.ref.WeakReference

class HotSalesAdapter: RecyclerView.Adapter<HotSalesAdapter.HotSalesItemViewHolder>() {

    private val photos = mutableListOf<Int>()
    private val brands = mutableListOf<String>()
    private val descriptions = mutableListOf<String>()
    private val isNew = mutableListOf<Boolean>()

    //fun setData()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_hot_sales, parent, false)
        return HotSalesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotSalesItemViewHolder, position: Int) {
        holder.photoLayout.setBackgroundResource(photos[position])
        if (isNew[position])
            holder.novelty.visibility = ConstraintLayout.VISIBLE
        else ConstraintLayout.INVISIBLE
        holder.brandName.text = brands[position]
        holder.itemDescription.text = descriptions[position]
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class HotSalesItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val view: WeakReference<View> = WeakReference(itemView)
        lateinit var photoLayout: ConstraintLayout
        lateinit var novelty: ConstraintLayout
        lateinit var brandName: TextView
        lateinit var itemDescription: TextView
        lateinit var buyNow: Button

        init {
            getViews()
        }

        private fun getViews() {

            view.get()?.let {

                photoLayout = it.findViewById(R.id.hot_sales_photo)
                novelty = it.findViewById(R.id.new_hot_sales_item)
                brandName = it.findViewById(R.id.item_brand_name)
                itemDescription = it.findViewById(R.id.hot_sales_description)
                buyNow = it.findViewById(R.id.hot_sales_buy_button)

            }
        }
    }

}