package com.smb.cabify.presentation

import android.os.Bundle
import com.smb.cabify.R
import com.smb.core.presentation.base.BaseActivity

class MainActivity : BaseActivity(
    layoutRes = R.layout.activity_main
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
    }
}