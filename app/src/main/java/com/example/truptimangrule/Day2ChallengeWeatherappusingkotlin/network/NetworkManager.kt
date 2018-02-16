package com.example.truptimangrule.weatherappusingkotlin.network

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by trupti.mangrule on 04/01/18.
 */
object NetworkManager {


    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}
