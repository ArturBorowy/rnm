package pl.arturborowy.rnm.base.ui.viewmodel

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface RxJavaSubscriber {

    val disposables: CompositeDisposable

    fun Disposable.addToSubs() {
        disposables.add(this)
    }

    fun clearSubs() = disposables.clear()
}