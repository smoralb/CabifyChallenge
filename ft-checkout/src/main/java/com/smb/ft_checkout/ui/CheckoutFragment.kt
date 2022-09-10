package com.smb.ft_checkout.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.smb.core.presentation.base.BaseFragment
import com.smb.ft_checkout.BR
import com.smb.ft_checkout.R
import com.smb.ft_checkout.databinding.FragmentCheckoutBinding
import com.smb.ft_checkout.ui.CheckoutState.NavigateUp
import com.smb.ft_checkout.ui.CheckoutState.ShowCheckoutCompleted
import com.smb.ft_checkout.ui.CheckoutState.ShowEmptyLayout
import com.smb.ft_checkout.ui.CheckoutState.ShowTotalAmount
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
    }

    override fun checkViewState(state: CheckoutState) {
        when (state) {
            NavigateUp -> viewModel.navigateBack(requireActivity())
            ShowEmptyLayout -> showEmptyState()
            ShowTotalAmount -> binding.csTotalAmount.visibility = View.VISIBLE
            ShowCheckoutCompleted -> {
                showEmptyState()
                showDialog()
            }
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(resources.getString(R.string.dialog_checkout_completed_title))
            .setMessage(resources.getString(R.string.dialog_checkout_completed_message))
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showEmptyState() {
        with(binding) {
            emptyLayout.visibility = View.VISIBLE
            csTotalAmount.visibility = View.GONE
        }
    }
}