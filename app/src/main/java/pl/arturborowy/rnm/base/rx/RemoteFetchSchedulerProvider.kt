package pl.arturborowy.rnm.base.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RemoteFetchSchedulerProvider : SchedulerProvider {

    override val subscribeOnScheduler: Scheduler
        get() = Schedulers.io()
    override val observeOnScheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}