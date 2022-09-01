package com.smb.cabify.presentation.main.home.mapper

import com.smb.cabify.domain.model.SampleChildDetailsModel
import com.smb.cabify.presentation.main.home.adapter.HomeDataItems

interface FirstFragmentMapper {
    fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: (String) -> Unit)
            : List<HomeDataItems.HomeDataItem>
}

class FirstFragmentMapperImpl : FirstFragmentMapper {

    override fun mapItems(model: List<SampleChildDetailsModel>, itemClickListener: (String) -> Unit) =
        model.map {
            HomeDataItems.HomeDataItem(
                isbn = it.isbn,
                title = it.title,
                description = it.description,
                publisher = it.publisher,
                onItemClickListener = itemClickListener
            )
        }
}