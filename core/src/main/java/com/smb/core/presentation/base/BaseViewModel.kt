package com.smb.core.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : BaseState> : ViewModel() {

    protected val viewState: MutableLiveData<S> = MutableLiveData()

    val _viewState: LiveData<S>
        get() = viewState
}