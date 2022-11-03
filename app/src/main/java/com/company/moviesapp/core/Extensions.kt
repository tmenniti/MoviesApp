package com.company.moviesapp.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Gravity
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.company.moviesapp.R

fun Context.showToast(message: String) {
    val toast : Toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER,0,0)
    toast.show()
}

fun Context.setImageGlide(urlPhoto: String, imageView: ImageView) {
    Glide.with(this)
        .load(urlPhoto)
        .centerCrop()
        //.error(R.drawable.ic_error)
        //.placeholder(R.drawable.ic_loading)
        .into(imageView)
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.isOnline() : Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) {
        when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                return true
            }
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                return true
            }
        }
    }
    return false
}