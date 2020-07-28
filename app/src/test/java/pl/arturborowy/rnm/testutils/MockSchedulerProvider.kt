package pl.arturborowy.rnm.testutils

import io.reactivex.schedulers.Schedulers
import pl.arturborowy.rnm.base.rx.SchedulerProvider

class MockSchedulerProvider : SchedulerProvider {
    override val subscribeOnScheduler = Schedulers.trampoline()
    override val observeOnScheduler = Schedulers.trampoline()
}