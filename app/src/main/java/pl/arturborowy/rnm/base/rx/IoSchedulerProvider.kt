package pl.arturborowy.rnm.base.rx

import io.reactivex.schedulers.Schedulers

class IoSchedulerProvider: SchedulerProvider {
    override val subscribeOnScheduler = Schedulers.io()
    override val observeOnScheduler = Schedulers.io()
}