package com.smb.ft_checkout.ui

import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_checkout.BR
import com.smb.ft_checkout.R
import com.smb.ft_checkout.databinding.FragmentCheckoutBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<CheckoutState, FragmentCheckoutBinding, CheckoutViewModel>(
    layoutResID = R.layout.fragment_checkout, BR.viewModel
) {

    override val viewModel: CheckoutViewModel by viewModel()

    override fun checkViewState(state: CheckoutState) {
        //Not yet implemented
    }
}