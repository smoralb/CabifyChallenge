package com.smb.ft_store

import android.os.Bundle
import com.smb.core.presentation.base.BaseActivity

class StoreActivity : BaseActivity(layoutRes = R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}