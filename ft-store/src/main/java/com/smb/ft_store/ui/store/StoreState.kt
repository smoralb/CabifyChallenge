package com.smb.ft_store.ui.store

import com.smb.core.presentation.base.BaseState

sealed class StoreState: BaseState() {
    object Loading: StoreState()
    object HideLoading: StoreState()
    data class NavigateToProductDetail(val code: String): StoreState()
}
