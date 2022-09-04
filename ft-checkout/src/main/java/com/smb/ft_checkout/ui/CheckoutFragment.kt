package com.smb.ft_checkout.ui

import android.os.Bundle
import android.view.View
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_checkout.BR
import com.smb.ft_checkout.R
import com.smb.ft_checkout.databinding.FragmentCheckoutBinding
import com.smb.ft_checkout.ui.adapter.CheckoutAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<CheckoutState, FragmentCheckoutBinding, CheckoutViewModel>(
    layoutResID = R.layout.fragment_checkout, BR.viewModel
) {

    override val viewModel: CheckoutViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCheckout.adapter = CheckoutAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: CheckoutState) {
        //Not yet implemented
    }
}