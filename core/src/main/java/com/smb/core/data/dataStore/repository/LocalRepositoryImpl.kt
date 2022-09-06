package com.smb.core.data.dataStore.repository

import com.smb.core.data.dataStore.source.CheckoutLocalSource
import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.core.domain.dataStore.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class LocalRepositoryImpl(
    private val localSource: CheckoutLocalSource
) : LocalRepository {

    override suspend fun getItems(): Flow<List<CheckoutModel>> =
        localSource.getItems()

    override suspend fun addNewItem(newItem: CheckoutModel) {
        localSource.addNewItem(newItem)
    }
}