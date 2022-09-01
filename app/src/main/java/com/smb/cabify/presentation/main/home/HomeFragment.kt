package com.smb.cabify.presentation.main.home

import android.os.Bundle
import android.view.View
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.databinding.FragmentFirstBinding
import com.smb.cabify.presentation.main.home.HomeViewState.*
import com.smb.cabify.presentation.main.home.adapter.HomeAdapter
import com.smb.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewState, FragmentFirstBinding, HomeViewModel>
    (R.layout.fragment_first, BR.viewModel) {

    override val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = HomeAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: HomeViewState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(FirstFragmentDirections.toSecondFragment(state.isbn))
        }
    }
}