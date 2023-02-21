package com.nathalie.todolistfragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nathalie.todolistfragments.data.Model.Image
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.databinding.ItemLayoutImageBinding
import com.nathalie.todolistfragments.utils.update

class ImageSliderAdapter(
    private var items: MutableList<Image>
) : RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemLayoutImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivImage.setImageResource(item.id)
    }

    override fun getItemCount() = items.size

    class ImageViewHolder(val binding: ItemLayoutImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setImages(items: List<Image>) {
        val oldItems = this.items
        this.items.clear()
        this.items.addAll(items)
        update(this.items, items) { image1, image2 ->
            image1.id == image2.id
        }
    }

}