package com.smb.ft_store.ui.store

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_store.BR
import com.smb.ft_store.R
import com.smb.ft_store.databinding.FragmentStoreBinding
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreState.Loading
import com.smb.ft_store.ui.store.StoreState.NavigateToProductDetail
import com.smb.ft_store.ui.store.StoreState.NavigateToStore
import com.smb.ft_store.ui.store.adapter.StoreAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<StoreState, FragmentStoreBinding, StoreViewModel>
    (R.layout.fragment_store, BR.viewModel) {

    override val viewModel by viewModel<StoreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvProducts.adapter = StoreAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: StoreState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToProductDetail ->
                navigateTo(StoreFragmentDirections.toSecondFragment(state.code))
            is NavigateToStore -> viewModel.navigateToStore()

        }
    }
}