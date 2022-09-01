package com.smb.cabify.presentation.home

import com.smb.core.presentation.base.BaseState

sealed class HomeViewState: BaseState() {
    object Loading: HomeViewState()
    object HideLoading: HomeViewState()
    data class NavigateToSecondFragment(val productCode: String): HomeViewState()
}
