package com.smb.cabify.presentation.secondView

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.smb.cabify.BR
import com.smb.cabify.R
import com.smb.cabify.databinding.FragmentSecondBinding
import com.smb.cabify.domain.model.ProductModel
import com.smb.core.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondFragment : BaseFragment<SecondViewState, FragmentSecondBinding, SecondViewModel>
    (R.layout.fragment_second, BR.viewModel) {

    override val viewModel by viewModel<SecondViewModel>()
    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(args.productModel)

        binding.tbHeader.setNavigationOnClickListener {

        }
    }

    override fun checkViewState(state: SecondViewState) {
        // Nothing to do yet
    }
}