package com.example.marvelapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.example.marvelapplication.data.remote.Resource
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun showToast(context : Context, message : String) = Toast.makeText(context,message, Toast.LENGTH_SHORT).show()

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }


    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Resource<T> {
        return try {
            val myResp = call.invoke()
            if (myResp.isSuccessful) {
                Resource.success(myResp.body()!!)
            } else {
                Resource.error(myResp.errorBody()?.string() ?: "Something goes wrong")
            }

        } catch (e: Exception) {
            Resource.error(e.message ?: "Internet error runs")
        }
    }
}