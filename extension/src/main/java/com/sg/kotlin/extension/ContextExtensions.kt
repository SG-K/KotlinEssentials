package com.sg.kotlin.extension

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.lang.ref.WeakReference

fun Context.hasNetwork(): Boolean? {
    var isConnected: Boolean? = false // Initial Value
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun Context.showToast(msg: String,length : Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg,
        Toast.LENGTH_SHORT).show()
}

val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels

fun Context.setSatatusBarColor(context: WeakReference<Activity>, @ColorRes colorResId: Int) {
    if (Build.VERSION.SDK_INT >= 21) {
        val window = context.get()?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = getRecourceColor(colorResId)
    }
}

fun Context.getRecourceColor(@ColorRes colorRes: Int) : Int{
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getRecourceString(@DrawableRes drawableRes: Int) : Drawable?{
    return ContextCompat.getDrawable(this, drawableRes)
}