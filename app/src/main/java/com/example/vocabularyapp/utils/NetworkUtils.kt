package com.example.vocabularyapp.utils

import android.content.Context
import android.net.*
import android.os.Build
import android.util.Log
import java.lang.Exception

fun isOnline(context: Context): Boolean {

    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val netInfo: NetworkCapabilities? =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (netInfo != null) {
            when {
                netInfo.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> { return true }
                netInfo.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> { return true }
                netInfo.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> { return true }
            }
        }
    } else {
        try {
            val netInfo = connectivityManager.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected) {
                Log.i("update_status", "Network is available : true")
                return true
            }
        } catch (e: Exception) {
            Log.i("update_status", e.message.toString())
        }
    }
    Log.i("update_status", "Network is available : FALSE")
    return false
}
