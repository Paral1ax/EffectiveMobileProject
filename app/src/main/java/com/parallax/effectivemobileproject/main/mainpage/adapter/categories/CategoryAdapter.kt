package com.parallax.effectivemobileproject.main.mainpage.adapter.categories

import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.model.main.CategoryItem

class CategoryAdapter(
    activity: Activity,
    private val onClickCategoryItem: (item: CategoryItem) -> Unit,
) :
    AdapterDelegate<List<CategoryItem>>() {

    private val inflater = activity.layoutInflater

    override fun isForViewType(items: List<CategoryItem>, position: Int): Boolean {
        return items[position] is CategoryItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            inflater.inflate(
                R.layout.single_category_recycler_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        items: List<CategoryItem>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position]

        with(holder as CategoryViewHolder) {
            image.setImageResource(item.drawableRes)
            categoryName.text = item.text
            itemView.setOnClickListener {
                onClickCategoryItem.invoke(item)
            }

            if (item.isSelected) {
                categoryView.setBackgroundResource(R.drawable.round_oragne_circle)
                image.setColorFilter(Color.WHITE)
            } else {
                categoryView.setBackgroundResource(R.drawable.round_white_circle)
                image.setColorFilter(Color.GRAY)
            }
        }
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var categoryView: View
        var categoryName: TextView

        init {
            image = itemView.findViewById(R.id.category_image_item)
            categoryView = itemView.findViewById(R.id.category_outer_view)
            categoryName = itemView.findViewById(R.id.category_name_text)
        }

    }
}