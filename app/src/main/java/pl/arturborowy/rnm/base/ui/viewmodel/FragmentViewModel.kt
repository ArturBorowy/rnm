package pl.arturborowy.rnm.base.ui.viewmodel

import androidx.annotation.IdRes
import androidx.databinding.ObservableField

abstract class FragmentViewModel : BaseViewModel() {

    val desiredDestination = ObservableField<Int>()

    fun navigate(@IdRes actionResId: Int) = desiredDestination.set(actionResId)

    open fun onAttach() {
    }

    open fun onCreate() {
    }

    open fun onCreateView() {
    }

    open fun onViewCreated() {
    }

    open fun onStart() {
    }

    open fun onResume() {
    }

    open fun onPause() {
    }

    open fun onStop() {
    }

    open fun onDestroyView() {
    }

    open fun onDetach() {
    }

    open fun onBackPressed(): Boolean {
        return false
    }
}