package pl.arturborowy.rnm.base.error

import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.ui.ToastInvoker
import pl.arturborowy.rnm.data.remote.base.NoConnectivityException
import timber.log.Timber
import java.util.concurrent.TimeoutException

class ThrowableHandler(private val toastInvoker: ToastInvoker) {

    fun handle(throwable: Throwable) {
        when (throwable) {
            is NoConnectivityException, is TimeoutException -> {
                Timber.w(throwable)
                toastInvoker.showLong(R.string.error_internet_not_available)
            }
            else -> {
                Timber.e(throwable)
                toastInvoker.showLong(R.string.error_unknown)
            }
        }
    }
}