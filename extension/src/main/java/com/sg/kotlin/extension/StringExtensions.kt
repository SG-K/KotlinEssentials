package com.sg.kotlin.extension

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.view.View

fun String?.valid() : Boolean{
    return this != null && this.trim().isNotEmpty() &&
            !this.equals("null",true)
}

fun String?.isValidEmail(target: String): Boolean {
    return this.valid() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches()
}



/**
 * Helps to set clickable part in text.
 *
 * Don't forget to set android:textColorLink="@color/link" (click selector) and
 * android:textColorHighlight="@color/window_background" (background color while clicks)
 * in the TextView where you will use this.
 */
fun SpannableString.withClickableSpan(clickablePart: String, onClickListener: () -> Unit): SpannableString {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) = onClickListener.invoke()
    }
    val clickablePartStart = indexOf(clickablePart)
    setSpan(clickableSpan,
        clickablePartStart,
        clickablePartStart + clickablePart.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}