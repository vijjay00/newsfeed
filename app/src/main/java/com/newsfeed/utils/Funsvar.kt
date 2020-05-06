@file:Suppress("DEPRECATION")

package com.newsfeed.utils

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import java.util.*

fun toast(message : String,mContext : Context){
    Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show()
}

 fun getDate(dateFormat: String): String {
     val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
     val outputFormat = SimpleDateFormat("dd-MM-yyyy")
     val date: Date = inputFormat.parse(dateFormat)
    return outputFormat.format(date).toString()
 }
fun isNetworkAvailable(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var activeNetworkInfo: NetworkInfo? = null
    activeNetworkInfo = cm.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
}
