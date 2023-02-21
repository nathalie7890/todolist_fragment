package com.nathalie.todolistfragments.adapters

import android.content.ClipData
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.databinding.ItemLayoutTaskBinding

class TaskAdapter(
    private var items: List<Task>,
//    val onClick: (item: Task) -> Unit,
//    val onLongClick: (item: Task) -> Unit,
//    val onMoreClick: (view: View, item: Task) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.ItemTaskHolder>() {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTaskHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutTaskBinding.inflate(layoutInflater, parent, false)
        return ItemTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemTaskHolder, position: Int) {
        val item = items[position]
        holder.binding.run {
            tvTitle.text = item.title
            tvDate.text = item.date
//            cvTaskItem.setOnClickListener(object: View.OnClickListener {
//                override fun onClick(v:View?) {
//                    onClick(item)
//                }
//            })

            cvTaskItem.setOnClickListener {
                listener?.onClick(item)
            }

            item.image?.let {bytes->
                val bitmap = BitmapFactory.decodeByteArray(item.image, 0, bytes.size)
                image.setImageBitmap(bitmap)
            }

            cvTaskItem.setOnLongClickListener {
                listener?.onLongClick(item)
                return@setOnLongClickListener true
            }

            icMore.setOnClickListener {
                listener?.onMoreClick(it, item)
            }
        }
    }

    override fun getItemCount() = items.size

    fun setTasks(items: List<Task>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ItemTaskHolder(val binding: ItemLayoutTaskBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Listener {
        fun onClick(task: Task)
        fun onLongClick(task: Task)
        fun onMoreClick(view: View, task: Task)

    }
}