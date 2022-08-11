package com.lutas.authme.util

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

fun <T> Fragment.observe(data: LiveData<T>, onChange: (T?) -> Unit) {
    data.observe(this.viewLifecycleOwner) {
        onChange(it)
    }
}

fun Fragment.observeVisibleEvent(data: LiveData<Event<Boolean>>, view: View) {
    data.observe(this) { event ->
        event.getContentIfNotHandled()?.let {
            view.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}