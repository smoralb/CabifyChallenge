package com.smb.core.data.dataStore.repository

import com.smb.core.data.dataStore.source.CheckoutLocalSource
import com.smb.core.domain.dataStore.model.CheckoutModel
import com.smb.core.domain.dataStore.repository.CheckoutRepository
import kotlinx.coroutines.flow.Flow

class CheckoutRepositoryImpl(
    private val localSource: CheckoutLocalSource
) : CheckoutRepository {

    override suspend fun getCheckoutItems(): Flow<List<CheckoutModel>> =
        localSource.getCheckoutItems()

    override suspend fun addNewItem(newItem: CheckoutModel) {
        localSource.addNewItem(newItem)
    }
}