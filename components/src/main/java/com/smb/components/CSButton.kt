package com.smb.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsButtonBinding

class CSButton(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private var quantity: Int = 1
        set(value) {
            field = value
            binding.tvQuantity.text = quantity.toString()
        }

    private val binding: CsButtonBinding =
        CsButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.apply {
            tvQuantity.text = quantity.toString()
            btAdd.setOnClickListener { quantity = quantity.inc() }
            btSubtract.setOnClickListener { quantity = quantity.dec() }
        }
    }

    fun setAddToCartButtonClickListener(action: (Int?) -> Unit) {
        binding.btAddToCart.setOnClickListener { action(quantity) }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("addToCartClickListener")
        fun setOnClickListener(view: CSButton, action: (Int?) -> Unit) {
            view.setAddToCartButtonClickListener(action)
        }
    }
}