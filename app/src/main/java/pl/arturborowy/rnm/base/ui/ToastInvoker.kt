package pl.arturborowy.rnm.base.ui

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

class ToastInvoker(private val applicationContext: Context) {

    fun showLong(@StringRes messageResId: Int) =
        showLong(applicationContext.getString(messageResId))

    fun showLong(message: String) = show(message, Toast.LENGTH_LONG)

    private fun show(message: String, length: Int) =
        Toast.makeText(applicationContext, message, length).show()

    fun showShort(@StringRes messageResId: Int) =
        showShort(applicationContext.getString(messageResId))

    fun showShort(message: String) = show(message, Toast.LENGTH_SHORT)
}