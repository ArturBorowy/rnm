package pl.arturborowy.rnm.base.ui.view

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

class LoadingScreenViewModel : ViewModel() {

    val loadingOverlayVisibility = ObservableField<Boolean>(false)

    fun show() = loadingOverlayVisibility.set(true)

    fun hide() = loadingOverlayVisibility.set(false)
}