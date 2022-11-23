package com.parallax.effectivemobileproject.main.mainpage.adapter.categories

import android.app.Activity
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.parallax.effectivemobileproject.main.mainpage.adapter.RecyclerViewClickListener
import com.parallax.effectivemobileproject.main.model.main.BestSellerItem
import com.parallax.effectivemobileproject.main.model.main.CategoryItem
import com.parallax.effectivemobileproject.main.model.main.Item

class MainCategoryAdapter(
    private val activity: Activity,
    private val categories: MutableList<CategoryItem>
) : ListDelegationAdapter<List<CategoryItem>>() {

    lateinit var delegate: RecyclerViewClickListener

    init {
        delegatesManager.addDelegate(
            CategoryAdapter(
                activity = activity,
                onClickCategoryItem = { item ->
                    val index = categories.indexOfFirst { it.text == item.text }

                    if (categories[index].isSelected) return@CategoryAdapter

                    val firstSelectedIndex = categories.indexOfFirst(CategoryItem::isSelected)

                    categories.apply {
                        set(index, categories[index].copy(isSelected = true))

                        if (firstSelectedIndex == -1) {
                            notifyItemChanged(index)
                            return@CategoryAdapter
                        }

                        set(
                            firstSelectedIndex,
                            categories[firstSelectedIndex].copy(isSelected = false)
                        )

                        notifyItemChanged(index)
                        notifyItemChanged(firstSelectedIndex)
                    }
                },
            )
        )


        setItems(categories)
    }


    fun attachDelegate(delegate: RecyclerViewClickListener) {
        this.delegate = delegate
    }
}