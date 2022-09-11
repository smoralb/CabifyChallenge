package com.smb.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.execute(useCase: suspend () -> Unit) {
    viewModelScope.launch { useCase() }
}