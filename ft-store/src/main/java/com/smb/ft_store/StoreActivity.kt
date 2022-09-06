package com.smb.ft_store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.smb.core.presentation.base.BaseActivity

class StoreActivity : BaseActivity(layoutRes = R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, StoreActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
    }
}