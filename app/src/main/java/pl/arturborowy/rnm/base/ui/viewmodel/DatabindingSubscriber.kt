package pl.arturborowy.rnm.base.ui.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableField

interface DatabindingSubscriber {

    val callbacks: MutableCollection<Pair<ObservableField<*>, Observable.OnPropertyChangedCallback>>

    fun Pair<ObservableField<*>, Observable.OnPropertyChangedCallback>.addToCallbacks() {
        callbacks.add(this)
    }

    fun clearCallbacks() {
        callbacks.forEach {
            val (observableField, callback) = it
            observableField.removeOnPropertyChangedCallback(callback)
        }
        callbacks.clear()
    }
}