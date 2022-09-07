package com.smb.ft_checkout

import android.content.Context
import android.content.Intent
import com.smb.core.presentation.base.BaseActivity

class CheckoutActivity : BaseActivity(layoutRes = R.layout.activity_cart) {

    companion object {
        fun newIntent(context: Context) = Intent(context, CheckoutActivity::class.java)
    }
}