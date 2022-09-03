package com.smb.ft_store.ui.store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smb.core.presentation.adapters.BaseAdapter
import com.smb.core.presentation.adapters.BaseItem
import com.smb.ft_store.BR
import com.smb.ft_store.R
import com.smb.ft_store.ui.store.adapter.StoreDataItems.StoreDataItem

class StoreAdapter(onItemClicked: (String) -> Unit) : BaseAdapter<StoreDataItem>(onItemClicked) {

    override fun updateData(newItems: List<StoreDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is StoreDataItem -> ITEM_TYPE
            else -> throw IllegalArgumentException()
        }

    private fun createItemViewHolder(parent: ViewGroup) =
        StoreItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_store,
                parent,
                false
            )
        )

    inner class StoreItemViewHolder(binding: ViewDataBinding) :
        BaseViewHolder<StoreDataItem>(BR.item, binding)

    companion object {
        const val ITEM_TYPE = 0
    }
}