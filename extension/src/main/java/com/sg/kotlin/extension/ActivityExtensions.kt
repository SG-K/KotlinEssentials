package com.sg.kotlin.extension

import android.app.Activity

inline fun <reified  T : Any> Activity.getValue(
    lable : String, defaultvalue : T? = null) = lazy{
    val value = intent?.extras?.get(lable)
    if (value is T) value else defaultvalue
}

inline fun <reified  T : Any> Activity.getValueNonNull(
    lable : String, defaultvalue : T? = null) = lazy{
    val value = intent?.extras?.get(lable)
    requireNotNull((if (value is T) value else defaultvalue)){lable}
}