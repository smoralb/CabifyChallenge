package com.smb.core.data.dataStore.repository

import com.smb.core.data.dataStore.source.LocalSource
import com.smb.core.domain.dataStore.model.ProductModel
import com.smb.core.domain.dataStore.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val localSource: LocalSource
) : LocalRepository {

    override suspend fun getItems(): Flow<List<ProductModel>> =
        localSource.getItems()

    override suspend fun addNewItem(newItem: ProductModel) {
        localSource.addNewItem(newItem)
    }

    override suspend fun clearItem(productId: String) =
        localSource.clearItem(productId)
}