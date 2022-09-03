package com.smb.cabify.presentation.home

import android.os.Bundle
import android.view.View
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.databinding.FragmentHomeBinding
import com.smb.cabify.domain.model.ProductModel
import com.smb.cabify.presentation.home.HomeViewState.HideLoading
import com.smb.cabify.presentation.home.HomeViewState.Loading
import com.smb.cabify.presentation.home.adapter.HomeAdapter
import com.smb.cabify.presentation.home.adapter.HomeDataItems.HomeDataItem
import com.smb.core.presentation.base.BaseFragment
import kotlinx.coroutines.processNextEventInCurrentThread
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewState, FragmentHomeBinding, HomeViewModel>
    (R.layout.fragment_home, BR.viewModel) {

    override val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter =
            HomeAdapter() {
                // This will be modified for another navigation model
                navigateTo(HomeFragmentDirections.toSecondFragment(
                    ProductModel(
                        code = (it as HomeDataItem).code,
                        name = it.name,
                        price = 10f,
                        image = ""
                    )
                ))
            }
        viewModel.initialize()
    }

    override fun checkViewState(state: HomeViewState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
        }
    }
}