package com.lutas.authme.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visible")
fun bindVisible(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageCircle")
fun bindImageLoader(view: ImageView, value: String?) {
    Glide
        .with(view)
        .load(value)
        .circleCrop()
        .placeholder(androidx.appcompat.R.color.material_blue_grey_800)
        .into(view)
}