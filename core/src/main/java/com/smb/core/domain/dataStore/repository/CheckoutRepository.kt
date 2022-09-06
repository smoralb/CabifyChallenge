package com.smb.core.domain.dataStore.repository

import com.smb.core.domain.dataStore.model.CheckoutModel
import kotlinx.coroutines.flow.Flow

interface CheckoutRepository {
    suspend fun getCheckoutItems(): Flow<List<CheckoutModel>>
    suspend fun addNewItem(newItem: CheckoutModel)
}