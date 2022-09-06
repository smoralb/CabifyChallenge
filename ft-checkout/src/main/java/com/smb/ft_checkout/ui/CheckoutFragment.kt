package com.smb.ft_checkout.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_checkout.BR
import com.smb.ft_checkout.R
import com.smb.ft_checkout.databinding.FragmentCheckoutBinding
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.adapter.CheckoutAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CheckoutFragment : BaseFragment<CheckoutState, FragmentCheckoutBinding, CheckoutViewModel>(
    layoutResID = R.layout.fragment_checkout, BR.viewModel
) {

    override val viewModel: CheckoutViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.navigateBack(requireActivity())
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvCheckout.adapter = CheckoutAdapter()
        viewModel.initialize()
    }

    override fun checkViewState(state: CheckoutState) {
        when (state) {
            NavigateUp -> viewModel.navigateBack(requireActivity())
        }
    }
}