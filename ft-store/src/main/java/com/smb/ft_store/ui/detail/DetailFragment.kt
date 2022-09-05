package com.smb.ft_store.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_store.BR
import com.smb.ft_store.R
import com.smb.ft_store.databinding.FragmentDetailBinding
import com.smb.ft_store.ui.detail.DetailState.HideLoading
import com.smb.ft_store.ui.detail.DetailState.Loading
import com.smb.ft_store.ui.detail.DetailState.NavigateUp
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailState, FragmentDetailBinding, DetailViewModel>
    (R.layout.fragment_detail, BR.viewModel) {

    override val viewModel by viewModel<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.productId)
    }

    override fun checkViewState(state: DetailState) {
        when (state) {
            Loading -> binding.plDetailsLoader.visibility = View.VISIBLE
            HideLoading -> binding.plDetailsLoader.visibility = View.GONE
            NavigateUp -> navigateUp()
        }
    }
}