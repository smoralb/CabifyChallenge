package com.smb.ft_store.ui.detail

import com.smb.core.presentation.base.BaseState

sealed class DetailState : BaseState() {
    object Loading: DetailState()
    object HideLoading: DetailState()
    object NavigateUp: DetailState()
}