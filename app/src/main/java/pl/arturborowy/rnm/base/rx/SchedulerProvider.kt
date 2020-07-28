package pl.arturborowy.rnm.base.rx

import io.reactivex.Scheduler

interface SchedulerProvider {

    val subscribeOnScheduler: Scheduler
    val observeOnScheduler: Scheduler
}