package com.smb.cabify.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.databinding.FragmentDetailBinding
import com.smb.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment<DetailState, FragmentDetailBinding, DetailViewModel>
    (R.layout.fragment_detail, BR.viewModel) {

    override val viewModel by viewModel<DetailViewModel>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.productModel)

        binding.tbHeader.setNavigationOnClickListener {

        }
    }

    override fun checkViewState(state: DetailState) {
        // Nothing to do yet
    }
}