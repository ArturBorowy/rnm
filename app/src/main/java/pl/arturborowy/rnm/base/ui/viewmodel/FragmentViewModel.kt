package pl.arturborowy.rnm.base.ui.viewmodel

abstract class FragmentViewModel : BaseViewModel() {

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