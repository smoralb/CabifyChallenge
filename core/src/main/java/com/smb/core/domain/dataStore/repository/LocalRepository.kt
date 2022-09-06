package com.smb.core.domain.dataStore.repository

import com.smb.core.domain.dataStore.model.CheckoutModel
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun getItems(): Flow<List<CheckoutModel>>
    suspend fun addNewItem(newItem: CheckoutModel)
}