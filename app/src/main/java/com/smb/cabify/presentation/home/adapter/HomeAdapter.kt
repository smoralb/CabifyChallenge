package com.smb.cabify.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.presentation.home.adapter.HomeDataItems.HomeDataItem
import com.smb.core.presentation.adapters.BaseAdapter
import com.smb.core.presentation.adapters.BaseItem

class HomeAdapter(onItemClicked: (BaseItem) -> Unit) : BaseAdapter<HomeDataItem>(onItemClicked) {

    override fun updateData(newItems: List<HomeDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is HomeDataItem -> ITEM_TYPE
            else -> throw IllegalArgumentException()
        }

    private fun createItemViewHolder(parent: ViewGroup) =
        FirstFragmentItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_store,
                parent,
                false
            )
        )

    inner class FirstFragmentItemViewHolder(binding: ViewDataBinding) :
        BaseViewHolder<HomeDataItem>(BR.item, binding)

    companion object {
        const val ITEM_TYPE = 0
    }
}