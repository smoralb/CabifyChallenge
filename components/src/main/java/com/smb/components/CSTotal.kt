package com.smb.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsTotalBinding

class CSTotal(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private val binding = CsTotalBinding.inflate(LayoutInflater.from(context), this)

    fun setTotalQuantity(total: String) {
        binding.tvTotalQuantity.text = total
    }

    fun setTotalVisibility(visibility: Int) {
        binding.content.visibility = visibility
    }

    companion object {
        @JvmStatic
        @BindingAdapter("totalQuantity")
        fun setTotalQuantity(view: CSTotal, total: String) {
            view.setTotalQuantity(total)
        }

        @JvmStatic
        @BindingAdapter("visibility")
        fun setVisibility(view: CSTotal, visibility: Int) {
            view.setTotalVisibility(visibility)
        }
    }
}