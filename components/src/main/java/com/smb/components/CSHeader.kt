package com.smb.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsHeaderBinding

class CSHeader(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private var binding: CsHeaderBinding =
        CsHeaderBinding.inflate(LayoutInflater.from(context), this)

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setHeaderNavigationIcon(icon: Int) {
        binding.ivToolbarCart.setImageDrawable(context.getDrawable(icon))
    }

    fun setHeaderTitle(title: Int) {
        binding.tvToolbarTitle.text = context.getString(title)
    }

    fun setNavigationIconClickListener(clickListener: () -> Unit) {
        binding.ivToolbarCart.setOnClickListener { clickListener() }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("headerTitle")
        fun setTitle(view: CSHeader, title: Int) {
            view.setHeaderTitle(title)
        }

        @JvmStatic
        @BindingAdapter("headerNavigationIcon")
        fun setNavigationIcon(view: CSHeader, icon: Int) {
            view.setHeaderNavigationIcon(icon)
        }

        @JvmStatic
        @BindingAdapter("navigationIconClickListener")
        fun setNavigationIconListener(view: CSHeader, action: () -> Unit) {
            view.setNavigationIconClickListener { action() }
        }

    }
}