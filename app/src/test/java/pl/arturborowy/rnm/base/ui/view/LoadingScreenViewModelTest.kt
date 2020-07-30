package pl.arturborowy.rnm.base.ui.view

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LoadingScreenViewModelTest {

    private val loadingScreenViewModel = LoadingScreenViewModel()

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