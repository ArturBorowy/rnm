package pl.arturborowy.rnm.data.remote.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable()) {
            throw NoConnectivityException()
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
    }
}

class NoConnectivityException : IOException()