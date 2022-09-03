package com.smb.ft_store.ui.store

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_store.BR
import com.smb.ft_store.R
import com.smb.ft_store.databinding.FragmentHomeBinding
import com.smb.ft_store.domain.model.ProductModel
import com.smb.ft_store.ui.store.StoreState.HideLoading
import com.smb.ft_store.ui.store.StoreState.Loading
import com.smb.ft_store.ui.store.adapter.StoreAdapter
import com.smb.ft_store.ui.store.adapter.StoreDataItems
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<StoreState, FragmentHomeBinding, StoreViewModel>
    (R.layout.fragment_home, BR.viewModel) {

    override val viewModel by viewModel<StoreViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter =
            StoreAdapter() {
                // This will be modified for another navigation model
                navigateTo(
                    StoreFragmentDirections.toSecondFragment(
                        ProductModel(
                            code = (it as StoreDataItems.StoreDataItem).code,
                            name = it.name,
                            description = it.description,
                            price = 10f,
                            image = it.image
                        )
                    )
                )
            }
        viewModel.initialize()
    }

    override fun checkViewState(state: StoreState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
        }
    }
}