package pl.arturborowy.rnm.base.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.processors.FlowableProcessor

fun <OnSuccessT> Single<OnSuccessT>.setSchedulers(schedulerProvider: SchedulerProvider):
        Single<OnSuccessT> =
    subscribeOn(schedulerProvider.subscribeOnScheduler)
        .observeOn(schedulerProvider.observeOnScheduler)

fun <OnSuccessT> Observable<OnSuccessT>.setSchedulers(schedulerProvider: SchedulerProvider):
        Observable<OnSuccessT> =
    subscribeOn(schedulerProvider.subscribeOnScheduler)
        .observeOn(schedulerProvider.observeOnScheduler)

fun Completable.setSchedulers(schedulerProvider: SchedulerProvider): Completable =
    subscribeOn(schedulerProvider.subscribeOnScheduler)
        .observeOn(schedulerProvider.observeOnScheduler)

fun <T> FlowableProcessor<T>.setSchedulers(schedulerProvider: SchedulerProvider): Flowable<T> =
    subscribeOn(schedulerProvider.subscribeOnScheduler)
        .observeOn(schedulerProvider.observeOnScheduler)