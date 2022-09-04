package com.smb.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.BindingAdapter
import com.smb.components.databinding.CsHeaderBinding

class CSHeader(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private var binding: CsHeaderBinding =
        CsHeaderBinding.inflate(LayoutInflater.from(context), this)

    fun setHeaderNavigationIcon(icon: Int) {
        binding.ivNavigation.setImageDrawable(context.getDrawable(icon))
    }

    fun setHeaderMenuIcon(icon: Int) {
        binding.ivMenu.setImageDrawable(context.getDrawable(icon))
    }

    fun setHeaderTitle(title: Int) {
        binding.tvToolbarTitle.text = context.getString(title)
    }

    fun setNavigationIconClickListener(clickListener: () -> Unit) {
        binding.ivNavigation.setOnClickListener { clickListener() }
    }

    fun setNavigationIconVisibility(visibility: Boolean) {
        binding.ivNavigation.visibility = when (visibility) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun setMenuIconVisibility(visibility: Boolean) {
        binding.ivMenu.visibility = when (visibility) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    fun setMenuIconClickListener(clickListener: () -> Unit) {
        binding.ivMenu.setOnClickListener { clickListener() }
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
        @BindingAdapter("navigationIconVisibility")
        fun setNavigationIconVisibility(view: CSHeader, visibility: Boolean) {
            view.setNavigationIconVisibility(visibility)
        }

        @JvmStatic
        @BindingAdapter("menuIconVisibility")
        fun setMenuIconVisibility(view: CSHeader, visibility: Boolean) {
            view.setMenuIconVisibility(visibility)
        }

        @JvmStatic
        @BindingAdapter("headerMenuIcon")
        fun setMenuIcon(view: CSHeader, icon: Int) {
            view.setHeaderMenuIcon(icon)
        }

        @JvmStatic
        @BindingAdapter("navigationIconClickListener")
        fun setNavigationIconListener(view: CSHeader, action: () -> Unit) {
            view.setNavigationIconClickListener { action() }
        }

        @JvmStatic
        @BindingAdapter("menuIconClickListener")
        fun setMenuIconListener(view: CSHeader, action: () -> Unit) {
            view.setMenuIconClickListener { action() }
        }

    }
}