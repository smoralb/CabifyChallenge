package com.smb.components

import android.content.Context
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsTotalBinding

class CSTotal(context: Context, attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {

    private val binding = CsTotalBinding.inflate(LayoutInflater.from(context), this)

    private fun timer(action: () -> Unit) {
        object : CountDownTimer(8000, 100) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                action()
            }
        }.start()
    }

    fun setTotalQuantity(total: String) {
        binding.tvTotalQuantity.text = total
    }

    fun setTotalVisibility(visibility: Int) {
        binding.content.visibility = visibility
    }

    fun setButtonClickListener(action: () -> Unit) {
        binding.flResult.setOnClickListener {
            binding.btnTitle.visibility = View.GONE
            binding.btnLoader.visibility = View.VISIBLE
            timer { binding.btnLoader.visibility = View.GONE }
            timer { binding.btnResult.visibility = View.VISIBLE }
            timer { binding.btnResult.setImageDrawable(context.getDrawable(R.drawable.ic_check)) }
            timer { action() }
        }
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

        @JvmStatic
        @BindingAdapter("onClickListener")
        fun setOnClickListener(view: CSTotal, action: () -> Unit) {
            view.setButtonClickListener(action)
        }
    }
}