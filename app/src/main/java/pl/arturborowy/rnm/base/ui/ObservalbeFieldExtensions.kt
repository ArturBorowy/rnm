package pl.arturborowy.rnm.base.ui

import androidx.databinding.Observable
import androidx.databinding.ObservableField

fun <ValueT> ObservableField<ValueT>.addOnPropertyChangedCallback(
    onPropertyChangedCallback: (ValueT?) -> Unit
): Pair<ObservableField<ValueT>, Observable.OnPropertyChangedCallback> {
    val callback = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) =
            onPropertyChangedCallback(get())
    }

    addOnPropertyChangedCallback(callback)

    return this to callback
}