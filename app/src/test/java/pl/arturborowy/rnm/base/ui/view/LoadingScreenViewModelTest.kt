package pl.arturborowy.rnm.base.ui.view

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import pl.arturborowy.rnm.base.di.definitionModule

class LoadingScreenViewModelTest : AutoCloseKoinTest() {

    private val loadingScreenViewModel by inject<LoadingScreenViewModel>()

    @Before
    fun setUp() {
        startKoin { modules(definitionModule) }
    }

    @Test
    fun `loadingOverlayVisibility get returns false by default`() {
        assertFalse(loadingScreenViewModel.loadingOverlayVisibility.get()!!)
    }

    @Test
    fun `show sets true on loadingOverlayVisibility`() {
        loadingScreenViewModel.show()

        assertTrue(loadingScreenViewModel.loadingOverlayVisibility.get()!!)
    }

    @Test
    fun `hide sets false on loadingOverlayVisibility`() {
        loadingScreenViewModel.hide()

        assertFalse(loadingScreenViewModel.loadingOverlayVisibility.get()!!)
    }
}