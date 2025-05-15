package com.nareshtech.shoemart.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

// TODO 8: Check the internet Connection (active/not) before making an actual network request.
fun isInternetAvailable(context: Context): Boolean{
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netwrok = connectivityManager.activeNetwork?:return false
    val capabilities = connectivityManager.getNetworkCapabilities(netwrok)?:return false
    return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}