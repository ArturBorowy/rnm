package pl.arturborowy.rnm.base.ui.viewmodel

abstract class ActivityViewModel : BaseViewModel() {

    open fun onCreate() {
    }

    open fun onStart() {
    }

    open fun onResume() {
    }

    open fun onPause() {
    }

    open fun onStop() {
    }
}