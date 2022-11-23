package com.parallax.effectivemobileproject.main.itempage.adapter.capacity

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.parallax.effectivemobileproject.R

class CapacityAdapter(
    activity: Activity,
    private val OnClickCapacityItem: (item: Int) -> Unit
): AdapterDelegate<List<Any>>() {

    private val inflater = activity.layoutInflater

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Int
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return CapacityViewHolder(inflater.inflate(R.layout.capacity_recycler_fragment, parent, false))
    }

    override fun onBindViewHolder(
        items: List<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) {
        val item = items[position] as Int

        with(holder as CapacityViewHolder) {
            capacity.text = "$item GB"
            itemView.setOnClickListener {
                OnClickCapacityItem.invoke(item)
            }
        }
    }

    class CapacityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var capacity: TextView
        var constraint: View

        init {
            capacity = itemView.findViewById(R.id.capacity_textview)
            constraint = itemView.findViewById(R.id.capacity_constraint)
        }
    }
}