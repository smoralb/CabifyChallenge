package com.smb.core.presentation.adapters

import android.annotation.SuppressLint
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

@SuppressLint("NotifyDataSetChanged")
abstract class BaseAdapter<T : BaseItem>(var onItemClicked: (BaseItem) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<T>()
        set(value) {
            if (value.isNotEmpty()) {
                field = value
                notifyDataSetChanged()
            }
        }

    abstract fun updateData(newItems: List<T>)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder<BaseItem>).bind(items[position])

    override fun getItemCount() = items.size

    open inner class BaseViewHolder<T>(
        private val itemVariableId: Int,
        private val binding: ViewDataBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            binding.apply {
                setVariable(itemVariableId, item)
                executePendingBindings()
                root.setOnClickListener { onItemClicked(item as BaseItem) }
            }
        }
    }
}