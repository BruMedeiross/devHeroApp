package com.brunadev.devheroapp.login.commom

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo

class NetworkChecker(private val connectivityManager: ConnectivityManager?) {

    fun performActionIfConnectet(action: () -> Unit){
        if (hasInternet()){
            action()
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun hasInternet(): Boolean {

        return if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.M) {
            val network: Network = connectivityManager?.activeNetwork
                ?: return false

            val capabilities: NetworkCapabilities = connectivityManager.getNetworkCapabilities(network)
                ?: return false

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    ||capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
        } else{
            val activeNetwork: NetworkInfo? = connectivityManager?.activeNetworkInfo
            if(activeNetwork != null){
                return activeNetwork.type == ConnectivityManager.TYPE_WIFI
                        || activeNetwork.type == ConnectivityManager.TYPE_MOBILE
            }
            false
        }
    }
}