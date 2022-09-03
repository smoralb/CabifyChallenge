package com.smb.ft_payment.presentation

import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_payment.BR
import com.smb.ft_payment.R
import com.smb.ft_payment.databinding.FragmentCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment() : BaseFragment<CartState, FragmentCartBinding, CartViewModel>(
    layoutResID = R.layout.fragment_cart, BR.viewModel
) {

    override val viewModel: CartViewModel by viewModel()

    override fun checkViewState(state: CartState) {
        //Not yet implemented
    }
}