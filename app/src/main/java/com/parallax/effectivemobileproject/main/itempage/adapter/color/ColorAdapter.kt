package com.parallax.effectivemobileproject.main.itempage.adapter.color

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R

class ColorAdapter(private val activity: Activity): AdapterDelegate<List<Any>>() {

    private val inflater = activity.layoutInflater

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is String
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return ColorViewHolder(inflater.inflate(R.layout.color_recycler_item, parent, false))
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val vh = holder as ColorViewHolder
        val current = items[position] as String
        val color = Color.parseColor(current)
        vh.color.background.setTint(color)
        vh.check.visibility = View.INVISIBLE
        vh.itemView.setOnClickListener {
            val constraint = it as ConstraintLayout
            val check = constraint.children.find { v ->
                v == v.findViewById(R.id.check_color_view)
            }
            if (check != null) {
                check.visibility = View.VISIBLE
            }
        }
    }

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var color: View
        var check: View

        init {
            color = itemView.findViewById(R.id.color_view)
            check = itemView.findViewById(R.id.check_color_view)
        }
    }
}