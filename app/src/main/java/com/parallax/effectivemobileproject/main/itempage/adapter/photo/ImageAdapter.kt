package com.parallax.effectivemobileproject.main.itempage.adapter.photo

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.request.ImageRequest
import com.parallax.effectivemobileproject.R
import com.parallax.effectivemobileproject.main.model.main.HomeStoreItem

class ImageAdapter(private val activity: Activity) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private val imageLoader = ImageLoader(activity)
    private val inflater = activity.layoutInflater
    private val image = mutableListOf<String>()

    fun setData(images: MutableList<String>) {
        image.clear()
        image.addAll(images)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(inflater.inflate(R.layout.viewpager_item_image, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val request = ImageRequest.Builder(activity)
            .data(image[position])
            .target { drawable ->
                holder.images.setImageDrawable(drawable)
            }
            .build()
        val disposable = imageLoader.enqueue(request)
    }

    override fun getItemCount(): Int {
        return image.size
    }


    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val images: ImageView = itemView.findViewById(R.id.item_imageview)
    }
}