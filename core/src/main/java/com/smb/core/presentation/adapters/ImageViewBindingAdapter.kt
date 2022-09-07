package com.smb.core.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImageUrl")
fun setImageUrl(view: ImageView, url: String) {
    view.load(url)
}