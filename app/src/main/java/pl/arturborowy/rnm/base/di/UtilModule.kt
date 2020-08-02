package pl.arturborowy.rnm.base.di

import androidx.databinding.Observable
import androidx.databinding.ObservableField
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.arturborowy.rnm.base.date.StringToDateConverter
import pl.arturborowy.rnm.base.date.YearMonthDayHourMinuteSecondMillisStringToDateConverter
import pl.arturborowy.rnm.base.error.ThrowableHandler
import pl.arturborowy.rnm.base.rx.RemoteFetchSchedulerProvider
import pl.arturborowy.rnm.base.rx.SchedulerProvider
import pl.arturborowy.rnm.base.ui.ToastInvoker
import pl.arturborowy.rnm.base.ui.fragment.BaseFragmentFactory
import pl.arturborowy.rnm.character.list.CharacterDiffCallback
import pl.arturborowy.rnm.domain.characters.model.CharactersDataSourceFactory
import java.util.*

val utilModule = module {
    single { BaseFragmentFactory() }

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

    single { ToastInvoker(get()) }

    single { ThrowableHandler(get()) }

    single { CharacterDiffCallback() }

    single { CharactersDataSourceFactory(get()) }
}