package pl.arturborowy.rnm.base.error

import io.mockk.*
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import pl.arturborowy.rnm.R
import pl.arturborowy.rnm.base.di.definitionModule
import pl.arturborowy.rnm.base.ui.ToastInvoker
import pl.arturborowy.rnm.data.remote.base.NoConnectivityException
import java.util.concurrent.TimeoutException

class ThrowableHandlerTest: AutoCloseKoinTest() {

    private val mockToastInvoker = mockk<ToastInvoker>()

    private val throwableHandler by inject<ThrowableHandler>()

    @Before
    fun setUp() {
        startKoin {
            modules(definitionModule + module {
                single(override = true) { mockToastInvoker }
            })
        }
    }

    @Test
    fun `handle calls showLong on toastInvoker with error_internet_not_available value when throwable is NoConnectivityException`() {
        every { mockToastInvoker.showLong(R.string.error_internet_not_available) } just Runs

        val throwable = NoConnectivityException()

        throwableHandler.handle(throwable)

        verify(exactly = 1) { mockToastInvoker.showLong(R.string.error_internet_not_available) }
    }

    @Test
    fun `handle calls showLong on toastInvoker with error_internet_not_available value when throwable is TimeoutException`() {
        every { mockToastInvoker.showLong(R.string.error_internet_not_available) } just Runs

        val throwable = TimeoutException()

        throwableHandler.handle(throwable)

        verify(exactly = 1) { mockToastInvoker.showLong(R.string.error_internet_not_available) }
    }

    @Test
    fun `handle calls showLong on toastInvoker with error_unknown value when throwable doesn't have set specific behavior`() {
        every { mockToastInvoker.showLong(R.string.error_unknown) } just Runs

        val throwable = object: Exception() {}

        throwableHandler.handle(throwable)

        verify(exactly = 1) { mockToastInvoker.showLong(R.string.error_unknown) }
    }
}