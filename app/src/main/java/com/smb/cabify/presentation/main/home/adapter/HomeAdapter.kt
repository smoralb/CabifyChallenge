package com.smb.cabify.presentation.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.core.presentation.adapters.BaseAdapter
import com.smb.core.presentation.adapters.BaseViewHolder

class HomeAdapter : BaseAdapter<HomeDataItems.HomeDataItem>() {

    override fun updateData(newItems: List<HomeDataItems.HomeDataItem>) {
        items = newItems
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ITEM_TYPE -> createItemViewHolder(parent)
            else -> throw IllegalArgumentException()
        }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is HomeDataItems.HomeDataItem -> ITEM_TYPE
            else -> throw IllegalArgumentException()
        }

    private fun createItemViewHolder(parent: ViewGroup) =
        FirstFragmentItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_sample_data,
                parent,
                false
            )
        )

    inner class FirstFragmentItemViewHolder(binding: ViewDataBinding) :
        BaseViewHolder<HomeDataItems.HomeDataItem>(BR.item, binding)

    companion object {
        const val ITEM_TYPE = 0
    }
}