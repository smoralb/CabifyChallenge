package com.smb.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsDiscountBinding

class CSDiscount(context: Context, attributeSet: AttributeSet) :
    FrameLayout(context, attributeSet) {

    private val binding: CsDiscountBinding =
        CsDiscountBinding.inflate(LayoutInflater.from(context), this)

    fun setAddToCartListener(action: () -> Unit) {
        binding.btAddToCartDiscount.setOnClickListener { action() }
    }

    fun setDiscountTitle(title: String) {
        binding.tvDiscount.text = title
    }

    fun setDiscountVisibility(isVisible: Boolean) {
        binding.clDiscountContent.visibility =
            when (isVisible) {
                true -> View.VISIBLE
                false -> View.GONE
            }
    }

    companion object {

        @JvmStatic
        @BindingAdapter("discountVisibility")
        fun setDiscountVisibility(view: CSDiscount, isVisible: Boolean) {
            view.setDiscountVisibility(isVisible)
        }

        @JvmStatic
        @BindingAdapter("addToCartClickListener")
        fun setAddToCartListener(view: CSDiscount, action: () -> Unit) {
            view.setAddToCartListener(action)
        }

        @JvmStatic
        @BindingAdapter("setDiscountTitle")
        fun setDiscountTitle(view: CSDiscount, title: String) {
            view.setDiscountTitle(title)
        }
    }
}