package com.parallax.effectivemobileproject.main.mainpage.hotsales.viewpager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.model.HomeStoreItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import java.lang.ref.WeakReference

class HotSalesAdapter(val context: Context, ): RecyclerView.Adapter<HotSalesAdapter.HotSalesItemViewHolder>() {

    val homeStoreList = mutableListOf<HomeStoreItem>()

    fun setData(hotSales: MutableList<HomeStoreItem>) {
        homeStoreList.clear()
        homeStoreList.addAll(hotSales)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotSalesItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_hot_sales, parent, false)
        return HotSalesItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotSalesItemViewHolder, position: Int) {
        Picasso.get()
            .load(homeStoreList[position].picture)
            .into(object : Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    holder.photoLayout.background = BitmapDrawable(context.resources, bitmap)
                }

                override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                    TODO("Not yet implemented")
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    TODO("Not yet implemented")
                }

            })
        //holder.photoLayout.setBackgroundResource(homeStoreList[position].picture)
        if (homeStoreList[position].is_new)
            holder.novelty.visibility = ConstraintLayout.VISIBLE
        else ConstraintLayout.INVISIBLE
        holder.brandName.text = homeStoreList[position].title
        holder.itemDescription.text = homeStoreList[position].subtitle
    }

    override fun getItemCount(): Int {
        return homeStoreList.size
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