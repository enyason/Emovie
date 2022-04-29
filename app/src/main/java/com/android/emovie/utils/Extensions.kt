package com.android.emovie.utils

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


fun ViewGroup.inflate(layout: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layout, this, attachToRoot)
}


fun ImageView.loadImage(image: Any?, placeholder: Int = 0, circular: Boolean = false) {
    Glide
        .with(context)
        .load(image)
        .placeholder(placeholder)
        .apply(
            if (circular) {
                RequestOptions.circleCropTransform()
            } else {
                RequestOptions.noTransformation()
            }
        )
        .into(this)
}


fun Activity.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Fragment.toast(message: String?, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, length).show()
}

fun Activity.launchActivity(intent: Intent) {
    startActivity(intent)
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.snack(msg: CharSequence, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, length).show()
}

fun Date?.convertTime(): String {
    if (this == null) return ""
    val formatter = SimpleDateFormat("hh:mm a", Locale.US)
    formatter.timeZone = TimeZone.getDefault()
    return formatter.format(this)
}

fun Double.formatBalance(): String {
    return NumberFormat.getCurrencyInstance(Locale("en", "NG")).format(this)
}

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun NavController.navigateSafe(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.let { navigate(direction) }
}

fun Fragment.getErrorString(data: Any): String {
    return when (data) {
        is Int -> getString(data)
        is String -> data
        else -> ""
    }

}
