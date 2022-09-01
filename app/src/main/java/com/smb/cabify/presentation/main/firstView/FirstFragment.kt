package com.smb.cabify.presentation.main.firstView

import android.os.Bundle
import android.view.View
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.databinding.FragmentFirstBinding
import com.smb.cabify.presentation.main.firstView.FirstViewState.*
import com.smb.cabify.presentation.main.firstView.adapter.FirstFragmentAdapter
import com.smb.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : BaseFragment<FirstViewState, FragmentFirstBinding, FirstViewModel>
    (R.layout.fragment_first, BR.viewModel) {

    override val viewModel by viewModel<FirstViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvBookList.adapter = FirstFragmentAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: FirstViewState) {
        when (state) {
            is Loading -> binding.plItemsLoader.visibility = View.VISIBLE
            is HideLoading -> binding.plItemsLoader.visibility = View.GONE
            is NavigateToSecondFragment ->
                navigateTo(FirstFragmentDirections.toSecondFragment(state.isbn))
        }
    }
}