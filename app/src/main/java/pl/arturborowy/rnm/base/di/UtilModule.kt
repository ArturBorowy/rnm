package pl.arturborowy.rnm.base.di

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.date.StringToDateConverter
import pl.arturborowy.rnm.base.date.YearMonthDayHourMinuteSecondMillisStringToDateConverter
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.ui.ItemBindingWrapper
import pl.arturborowy.rnm.base.ui.fragment.BaseFragmentFactory
import java.util.*

val utilModule = module {
    single { ClassLoader.getSystemClassLoader() }
    single { BaseFragmentFactory(get()) }

    factory { CompositeDisposable() }

    single<SchedulerProvider>(named<RemoteFetchSchedulerProvider>()) {
        RemoteFetchSchedulerProvider()
    }

    single<StringToDateConverter>(
        named<YearMonthDayHourMinuteSecondMillisStringToDateConverter>()
    ) {
        YearMonthDayHourMinuteSecondMillisStringToDateConverter(Locale.US)
    }

    factory<MutableCollection<Pair<ObservableField<*>, Observable.OnPropertyChangedCallback>>> {
        mutableSetOf()
    }

    single { ItemBindingWrapper() }
}