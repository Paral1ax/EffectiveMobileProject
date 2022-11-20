package com.parallax.effectivemobileproject.main.mainpage.adapter.categories

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.model.main.CategoryItem

class CategoryAdapter(private val activity: Activity): AdapterDelegate<List<CategoryItem>>() {

    private val inflater = activity.layoutInflater

    override fun isForViewType(items: List<CategoryItem>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoryViewHolder(inflater.inflate(R.layout.single_category_recycler_item, parent, false))
    }

    override fun onBindViewHolder(
        items: List<CategoryItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as CategoryViewHolder
        if (position == 0) {
            vh.categoryView.setBackgroundResource(R.drawable.round_oragne_circle)
        }
        vh.image.setImageResource(items[position].vector)
    }

    class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var categoryView: View

        init {
            image = itemView.findViewById(R.id.category_image_item)
            categoryView = itemView.findViewById(R.id.category_outer_view)
        }
    }
}