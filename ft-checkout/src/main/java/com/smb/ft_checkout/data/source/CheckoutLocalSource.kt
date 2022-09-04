package com.smb.ft_checkout.data.source

import androidx.datastore.core.DataStore
import com.smb.ft_checkout.ShoppingCart
import com.smb.ft_checkout.domain.model.CheckoutModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface CheckoutLocalSource {
    fun getCheckoutItems(): Flow<List<CheckoutModel>>
}

class CheckoutLocalSourceImpl(
    private val dataStore: DataStore<ShoppingCart>
) : CheckoutLocalSource {

    override fun getCheckoutItems(): Flow<List<CheckoutModel>> =
        dataStore.data.map {
            it.itemsList.map { item ->
                CheckoutModel(
                    id = item.id,
                    title = item.name,
                    image = item.image,
                    price = item.price,
                    quantity = item.quantity
                )
            }
        }


}