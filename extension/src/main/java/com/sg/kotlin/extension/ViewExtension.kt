package com.sg.kotlin.extension

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.drawable.TintAwareDrawable

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.remove() {
    this.visibility = View.GONE
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSoftKeyboard(view2: View?) {
    if (view2 != null) {
        view2?.requestFocus()
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);

        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

    }
}


fun View?.click(block: () -> Unit) {
    this?.setOnClickListener {
        block()
    }
}

fun View.disableButton() {
    isEnabled = false
    alpha = 0.7f
}

fun View.enableButton() {
    isEnabled = true
    alpha = 1.0f
}

fun ImageView.tintSrc(@ColorRes colorRes: Int) {
    val drawable = DrawableCompat.wrap(drawable)
    DrawableCompat.setTint(drawable, this.context.getRecourceColor(colorRes))
    setImageDrawable(drawable)
    if (drawable is TintAwareDrawable) invalidate() // Because in this case setImageDrawable will not call invalidate()
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}
